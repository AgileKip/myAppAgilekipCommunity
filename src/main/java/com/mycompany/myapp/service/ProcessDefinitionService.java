package com.mycompany.myapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.domain.ProcessDefinition;
import com.mycompany.myapp.domain.enumeration.StatusProcessDefinition;
import com.mycompany.myapp.repository.ProcessDefinitionRepository;
import com.mycompany.myapp.service.dto.ProcessDefinitionDTO;
import com.mycompany.myapp.service.mapper.ProcessDefinitionMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.xml.type.ModelElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProcessDefinition}.
 */
@Service
@Transactional
public class ProcessDefinitionService {

    private static final String ENTITY_NAME = "processDefinition";

    private final Logger log = LoggerFactory.getLogger(ProcessDefinitionService.class);

    private final ProcessDefinitionRepository processDefinitionRepository;

    private final ProcessDefinitionMapper processDefinitionMapper;

    public ProcessDefinitionService(
        ProcessDefinitionRepository processDefinitionRepository,
        ProcessDefinitionMapper processDefinitionMapper
    ) {
        this.processDefinitionRepository = processDefinitionRepository;
        this.processDefinitionMapper = processDefinitionMapper;
    }

    public ProcessDefinition createOrUpdateProcessDefinition(BpmnModelInstance bpmnModelInstance) {
        Process process = extracAndValidProcessFromModel(bpmnModelInstance);
        Optional<ProcessDefinition> optionalProcessDefinition = processDefinitionRepository.findByBpmnProcessDefinitionId(process.getId());

        if (optionalProcessDefinition.isPresent()) {
            return updateProcessDefinition(process);
        }

        return createProcessDefinition(process);
    }

    private Process extracAndValidProcessFromModel(BpmnModelInstance modelInstance) {
        ModelElementType processType = modelInstance.getModel().getType(Process.class);
        Process process = (Process) modelInstance.getModelElementsByType(processType).iterator().next();

        if (!process.isExecutable()) {
            throw new BadRequestAlertException(
                "Model is not executable",
                ENTITY_NAME,
                "myAppAgilekipCommunityApp.processDefinition.error.bpmnProcessIsNotExecutable"
            );
        }

        if (StringUtils.isBlank(process.getName())) {
            throw new BadRequestAlertException(
                "Process name is not provided",
                ENTITY_NAME,
                "myAppAgilekipCommunityApp.processDefinition.error.bpmnNameNotProvided"
            );
        }

        return process;
    }

    private ProcessDefinition createProcessDefinition(Process process) {
        ProcessDefinition processDefinition = new ProcessDefinition();
        processDefinition.setBpmnProcessDefinitionId(process.getId());
        processDefinition.setName(process.getName());
        processDefinition.setCanBeManuallyStarted(process.isCamundaStartableInTasklist());
        processDefinition.setStatus(StatusProcessDefinition.ACTIVE);
        if (!process.getDocumentations().isEmpty()) {
            processDefinition.setDescription(process.getDocumentations().iterator().next().getRawTextContent());
        }

        return processDefinitionRepository.save(processDefinition);
    }

    private ProcessDefinition updateProcessDefinition(Process process) {
        ProcessDefinition processDefinition = processDefinitionRepository.findByBpmnProcessDefinitionId(process.getId()).orElseThrow();
        processDefinition.setName(process.getName());
        processDefinition.setCanBeManuallyStarted(process.isCamundaStartableInTasklist());
        processDefinition.setStatus(StatusProcessDefinition.ACTIVE);
        if (!process.getDocumentations().isEmpty()) {
            processDefinition.setDescription(process.getDocumentations().iterator().next().getRawTextContent());
        }

        return processDefinitionRepository.save(processDefinition);
    }

    /**
     * Get all the processDefinitions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProcessDefinitionDTO> findAll() {
        log.debug("Request to get all ProcessDefinitions");
        return processDefinitionRepository
            .findAll()
            .stream()
            .map(processDefinitionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one processDefinition by id.
     *
     * @param idOrBpmnProcessDefinitionId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProcessDefinitionDTO> findByIdOrBpmnProcessDefinitionId(String idOrBpmnProcessDefinitionId) {
        log.debug("Request to get ProcessDefinition : {}", idOrBpmnProcessDefinitionId);
        if (StringUtils.isNumeric(idOrBpmnProcessDefinitionId)) {
            return processDefinitionRepository.findById(Long.parseLong(idOrBpmnProcessDefinitionId)).map(processDefinitionMapper::toDto);
        }
        return processDefinitionRepository.findByBpmnProcessDefinitionId(idOrBpmnProcessDefinitionId).map(processDefinitionMapper::toDto);
    }

    /**
     * Delete the processDefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProcessDefinition : {}", id);
        processDefinitionRepository.deleteById(id);
    }

    public static void main(String[] args) {
        try {
            File f = new File(
                    "/Users/utelemaco/development/workspaceJHipster7/blueprint-example/anotherProcess/anotherProcess.bpmn"
            );
            FileInputStream fis = new FileInputStream(f);
            BpmnModelInstance modelInstance = Bpmn.readModelFromStream(fis);

            ProcessDefinitionService processDefinitionService = new ProcessDefinitionService(null, null);
            Process process = processDefinitionService.extracAndValidProcessFromModel(modelInstance);
            if (!process.getDocumentations().isEmpty()) {
                System.out.println(process.getDocumentations().iterator().next().getRawTextContent());
            }

            if (process.getExtensionElements() != null && process.getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).count() > 0) {
                Map<String, String> deploymentProperties = new HashMap<>();
                CamundaProperties camundaProperties = process.getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult();
                camundaProperties.getCamundaProperties().forEach(camundaProperty -> {
                    deploymentProperties.put(camundaProperty.getCamundaName(), camundaProperty.getCamundaValue());
                    System.out.println("Camunda Property Id: " + camundaProperty.getCamundaId());
                    System.out.println("Camunda Property Name: " + camundaProperty.getCamundaName());
                    System.out.println("Camunda Property Value: " + camundaProperty.getCamundaValue());
                });
                ObjectMapper objectMapper = new ObjectMapper();
                String s = objectMapper.writeValueAsString(deploymentProperties);
                System.out.println(deploymentProperties);
                Map<String, String> map = objectMapper.readValue(s, new TypeReference<Map<String, String>>(){});
                System.out.println(map);

            }



        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

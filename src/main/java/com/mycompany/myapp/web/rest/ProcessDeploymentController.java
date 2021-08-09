package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProcessDeploymentService;
import com.mycompany.myapp.service.dto.ProcessDeploymentBpmnModelDTO;
import com.mycompany.myapp.service.dto.ProcessDeploymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ProcessDeployment}.
 */
@RestController
@RequestMapping("/api")
public class ProcessDeploymentController {

    private final Logger log = LoggerFactory.getLogger(ProcessDeploymentController.class);

    private static final String ENTITY_NAME = "processDeployment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProcessDeploymentService processDeploymentService;

    public ProcessDeploymentController(ProcessDeploymentService processDeploymentService) {
        this.processDeploymentService = processDeploymentService;
    }

    /**
     * {@code POST  /process-definitions} : Create a new processDefinition.
     *
     * @param processDeploymentDTO the processDeploymentDTO to deploy.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new processDeploymentDTO, or with status {@code 400 (Bad Request)} if the processDeploymentDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/process-deployment/deploy")
    public ResponseEntity<Void> deploy(@RequestBody ProcessDeploymentDTO processDeploymentDTO)
        throws URISyntaxException {
        log.debug("REST request to deploy ProcessDeployment : {}", processDeploymentDTO);
        ProcessDeploymentDTO result = processDeploymentService.deploy(processDeploymentDTO);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCamundaProcessDefinitionId()))
                .build();
    }

    /**
     * {@code GET  /process-definition-deployment/:id} : get the processDeployment.
     *
     * @param id the id of the processDeployment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/process-deployment/{id}")
    public ResponseEntity<ProcessDeploymentDTO> getProcessDeployment(@PathVariable Long id) {
        log.debug("REST request to get ProcessDeployment : {}", id);
        Optional<ProcessDeploymentDTO> result = processDeploymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /process-definition-deployment/:id} : get the processDeployment.
     *
     * @param id the id of the processDeployment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/process-deployment/{id}/bpmnModel")
    public ResponseEntity<ProcessDeploymentBpmnModelDTO> getProcessDeploymentBpmnModel(@PathVariable Long id) {
        log.debug("REST request to get ProcessDeployment : {}", id);
        Optional<ProcessDeploymentBpmnModelDTO> result = processDeploymentService.findBpmnModel(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     * {@code GET  /process-definition-deployment/:id/active} : active the "id" processDeployment.
     *
     * @param id the id of the processDeployment to inactive.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/process-deployment/{id}/active")
    public ResponseEntity<Void> activeProcessDeployment(@PathVariable Long id) {
        log.debug("REST request to inactive ProcessDeployment : {}", id);
        processDeploymentService.activate(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * {@code GET  /process-definition-deployment/:id/inactive} : inactive the "id" processDeployment.
     *
     * @param id the id of the processDeployment to inactive.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/process-deployment/{id}/inactive")
    public ResponseEntity<Void> inactiveProcessDeployment(@PathVariable Long id) {
        log.debug("REST request to inactive ProcessDeployment : {}", id);
        processDeploymentService.inactivate(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

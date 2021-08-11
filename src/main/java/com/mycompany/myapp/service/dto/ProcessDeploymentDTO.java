package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.StatusProcessDeployment;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ProcessDeployment} entity.
 */
public class ProcessDeploymentDTO implements Serializable {

    private Long id;

    private StatusProcessDeployment status;

    private byte[] specificationFile;

    private String specificationFileContentType;

    private String camundaDeploymentMessage;

    private String camundaDeploymentId;

    private String camundaProcessDefinitionId;

    private LocalDateTime deployDate;

    private LocalDateTime activationDate;

    private LocalDateTime inactivationDate;

    private ProcessDefinitionDTO processDefinition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusProcessDeployment getStatus() {
        return status;
    }

    public void setStatus(StatusProcessDeployment status) {
        this.status = status;
    }

    public byte[] getSpecificationFile() {
        return specificationFile;
    }

    public void setSpecificationFile(byte[] specificationFile) {
        this.specificationFile = specificationFile;
    }

    public String getSpecificationFileContentType() {
        return specificationFileContentType;
    }

    public void setSpecificationFileContentType(String specificationFileContentType) {
        this.specificationFileContentType = specificationFileContentType;
    }

    public String getCamundaDeploymentMessage() {
        return camundaDeploymentMessage;
    }

    public void setCamundaDeploymentMessage(String camundaDeploymentMessage) {
        this.camundaDeploymentMessage = camundaDeploymentMessage;
    }

    public String getCamundaDeploymentId() {
        return camundaDeploymentId;
    }

    public void setCamundaDeploymentId(String camundaDeploymentId) {
        this.camundaDeploymentId = camundaDeploymentId;
    }

    public String getCamundaProcessDefinitionId() {
        return camundaProcessDefinitionId;
    }

    public void setCamundaProcessDefinitionId(String camundaProcessDefinitionId) {
        this.camundaProcessDefinitionId = camundaProcessDefinitionId;
    }

    public LocalDateTime getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(LocalDateTime deployDate) {
        this.deployDate = deployDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDateTime getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(LocalDateTime inactivationDate) {
        this.inactivationDate = inactivationDate;
    }

    public ProcessDefinitionDTO getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinitionDTO processDefinition) {
        this.processDefinition = processDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessDeploymentDTO)) {
            return false;
        }

        ProcessDeploymentDTO processDefinitionDTO = (ProcessDeploymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, processDefinitionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcessDeploymentDTO{" +
            "id=" + getId() +
            ", camundaProcessDefinitionId='" + camundaProcessDefinitionId + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

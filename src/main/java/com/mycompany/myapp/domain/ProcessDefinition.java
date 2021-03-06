package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.StatusProcessDefinition;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A ProcessDefinition.
 */
@Entity
@Table(name = "process_definition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProcessDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusProcessDefinition status;

    @Column(name = "bpmn_process_definition_id")
    private String bpmnProcessDefinitionId;

    @Column(name = "can_be_manually_started")
    private Boolean canBeManuallyStarted;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessDefinition id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusProcessDefinition getStatus() {
        return this.status;
    }

    public void setStatus(StatusProcessDefinition status) {
        this.status = status;
    }

    public String getBpmnProcessDefinitionId() {
        return this.bpmnProcessDefinitionId;
    }

    public void setBpmnProcessDefinitionId(String bpmnProcessDefinitionId) {
        this.bpmnProcessDefinitionId = bpmnProcessDefinitionId;
    }

    public Boolean getCanBeManuallyStarted() {
        return canBeManuallyStarted;
    }

    public void setCanBeManuallyStarted(Boolean canBeManuallyStarted) {
        this.canBeManuallyStarted = canBeManuallyStarted;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessDefinition)) {
            return false;
        }
        return id != null && id.equals(((ProcessDefinition) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProcessDefinition{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", bpmnProcessDefinitionId='" + getBpmnProcessDefinitionId() + "'" +
            "}";
    }
}

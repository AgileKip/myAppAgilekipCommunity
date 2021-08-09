package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProcessDefinition;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProcessDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinition, Long> {

    Optional<ProcessDefinition> findByBpmnProcessDefinitionId(String bpmnProcessDefinitionId);

}

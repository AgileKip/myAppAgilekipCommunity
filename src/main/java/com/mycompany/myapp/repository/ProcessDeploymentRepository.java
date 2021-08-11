package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProcessDeployment;
import com.mycompany.myapp.domain.enumeration.StatusProcessDeployment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProcessDeployment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessDeploymentRepository extends JpaRepository<ProcessDeployment, Long> {
    List<ProcessDeployment> findByProcessDefinitionId(Long processDefinitionId);

    Optional<ProcessDeployment> findByCamundaProcessDefinitionId(String camundaProcessDefinitionId);

    @Query(
        "from ProcessDeployment where processDefinition.id = ?1 and status = com.mycompany.myapp.domain.enumeration.StatusProcessDeployment.ACTIVE"
    )
    Optional<ProcessDeployment> findByProcessDefinitionIdAndStatusIsActive(Long processDefinitionId);

    @Modifying
    @Query("update ProcessDeployment set status = ?1 where id = ?2")
    void updateStatusById(StatusProcessDeployment status, Long id);

    @Modifying
    @Query("update ProcessDeployment set activationDate = ?1 where id = ?2")
    void updateActivationDateById(LocalDateTime localDateTime, Long id);

    @Modifying
    @Query("update ProcessDeployment set inactivationDate = ?1 where id = ?2")
    void updateInactivationDateById(LocalDateTime localDateTime, Long id);
}

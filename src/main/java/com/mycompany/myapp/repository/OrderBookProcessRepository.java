package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrderBookProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderBookProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderBookProcessRepository extends JpaRepository<OrderBookProcess, Long> {
    Optional<OrderBookProcess> findByProcessInstanceId(Long processInstanceId);
}

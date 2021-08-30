package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OrderBook;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OrderBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {}

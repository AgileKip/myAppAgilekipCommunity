package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OrderBook;
import com.mycompany.myapp.repository.OrderBookRepository;
import com.mycompany.myapp.service.dto.OrderBookDTO;
import com.mycompany.myapp.service.mapper.OrderBookMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderBook}.
 */
@Service
@Transactional
public class OrderBookService {

    private final Logger log = LoggerFactory.getLogger(OrderBookService.class);

    private final OrderBookRepository orderBookRepository;

    private final OrderBookMapper orderBookMapper;

    public OrderBookService(OrderBookRepository orderBookRepository, OrderBookMapper orderBookMapper) {
        this.orderBookRepository = orderBookRepository;
        this.orderBookMapper = orderBookMapper;
    }

    /**
     * Save a orderBook.
     *
     * @param orderBookDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderBookDTO save(OrderBookDTO orderBookDTO) {
        log.debug("Request to save OrderBook : {}", orderBookDTO);
        OrderBook orderBook = orderBookMapper.toEntity(orderBookDTO);
        orderBook = orderBookRepository.save(orderBook);
        return orderBookMapper.toDto(orderBook);
    }

    /**
     * Partially update a orderBook.
     *
     * @param orderBookDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderBookDTO> partialUpdate(OrderBookDTO orderBookDTO) {
        log.debug("Request to partially update OrderBook : {}", orderBookDTO);

        return orderBookRepository
            .findById(orderBookDTO.getId())
            .map(
                existingOrderBook -> {
                    orderBookMapper.partialUpdate(existingOrderBook, orderBookDTO);
                    return existingOrderBook;
                }
            )
            .map(orderBookRepository::save)
            .map(orderBookMapper::toDto);
    }

    /**
     * Get all the orderBooks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderBookDTO> findAll() {
        log.debug("Request to get all OrderBooks");
        return orderBookRepository.findAll().stream().map(orderBookMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderBookDTO> findOne(Long id) {
        log.debug("Request to get OrderBook : {}", id);
        return orderBookRepository.findById(id).map(orderBookMapper::toDto);
    }

    /**
     * Delete the orderBook by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderBook : {}", id);
        orderBookRepository.deleteById(id);
    }
}

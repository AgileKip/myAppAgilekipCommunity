package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.OrderBookRepository;
import com.mycompany.myapp.service.OrderBookService;
import com.mycompany.myapp.service.dto.OrderBookDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderBook}.
 */
@RestController
@RequestMapping("/api")
public class OrderBookResource {

    private final Logger log = LoggerFactory.getLogger(OrderBookResource.class);

    private static final String ENTITY_NAME = "orderBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderBookService orderBookService;

    private final OrderBookRepository orderBookRepository;

    public OrderBookResource(OrderBookService orderBookService, OrderBookRepository orderBookRepository) {
        this.orderBookService = orderBookService;
        this.orderBookRepository = orderBookRepository;
    }

    /**
     * {@code POST  /order-books} : Create a new orderBook.
     *
     * @param orderBookDTO the orderBookDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderBookDTO, or with status {@code 400 (Bad Request)} if the orderBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-books")
    public ResponseEntity<OrderBookDTO> createOrderBook(@Valid @RequestBody OrderBookDTO orderBookDTO) throws URISyntaxException {
        log.debug("REST request to save OrderBook : {}", orderBookDTO);
        if (orderBookDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderBookDTO result = orderBookService.save(orderBookDTO);
        return ResponseEntity
            .created(new URI("/api/order-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-books/:id} : Updates an existing orderBook.
     *
     * @param id the id of the orderBookDTO to save.
     * @param orderBookDTO the orderBookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderBookDTO,
     * or with status {@code 400 (Bad Request)} if the orderBookDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderBookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-books/{id}")
    public ResponseEntity<OrderBookDTO> updateOrderBook(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OrderBookDTO orderBookDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderBook : {}, {}", id, orderBookDTO);
        if (orderBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderBookDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderBookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderBookDTO result = orderBookService.save(orderBookDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderBookDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /order-books/:id} : Partial updates given fields of an existing orderBook, field will ignore if it is null
     *
     * @param id the id of the orderBookDTO to save.
     * @param orderBookDTO the orderBookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderBookDTO,
     * or with status {@code 400 (Bad Request)} if the orderBookDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderBookDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderBookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/order-books/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<OrderBookDTO> partialUpdateOrderBook(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OrderBookDTO orderBookDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderBook partially : {}, {}", id, orderBookDTO);
        if (orderBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderBookDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderBookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderBookDTO> result = orderBookService.partialUpdate(orderBookDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderBookDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-books} : get all the orderBooks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderBooks in body.
     */
    @GetMapping("/order-books")
    public List<OrderBookDTO> getAllOrderBooks() {
        log.debug("REST request to get all OrderBooks");
        return orderBookService.findAll();
    }

    /**
     * {@code GET  /order-books/:id} : get the "id" orderBook.
     *
     * @param id the id of the orderBookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderBookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-books/{id}")
    public ResponseEntity<OrderBookDTO> getOrderBook(@PathVariable Long id) {
        log.debug("REST request to get OrderBook : {}", id);
        Optional<OrderBookDTO> orderBookDTO = orderBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderBookDTO);
    }

    /**
     * {@code DELETE  /order-books/:id} : delete the "id" orderBook.
     *
     * @param id the id of the orderBookDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-books/{id}")
    public ResponseEntity<Void> deleteOrderBook(@PathVariable Long id) {
        log.debug("REST request to delete OrderBook : {}", id);
        orderBookService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

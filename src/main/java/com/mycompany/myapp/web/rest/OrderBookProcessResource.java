package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.OrderBookProcessService;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.OrderBookProcess}.
 */
@RestController
@RequestMapping("/api")
public class OrderBookProcessResource {

    private final Logger log = LoggerFactory.getLogger(OrderBookProcessResource.class);

    private static final String ENTITY_NAME = "orderBookProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderBookProcessService orderBookProcessService;

    public OrderBookProcessResource(OrderBookProcessService orderBookProcessService) {
        this.orderBookProcessService = orderBookProcessService;
    }

    /**
     * {@code POST  /order-book-processes} : Create a new orderBookProcess.
     *
     * @param orderBookProcessDTO the orderBookProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderBookProcessDTO, or with status {@code 400 (Bad Request)} if the orderBookProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-book-processes")
    public ResponseEntity<OrderBookProcessDTO> create(@RequestBody OrderBookProcessDTO orderBookProcessDTO) throws URISyntaxException {
        log.debug("REST request to save OrderBookProcess : {}", orderBookProcessDTO);
        if (orderBookProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderBookProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderBookProcessDTO result = orderBookProcessService.create(orderBookProcessDTO);
        return ResponseEntity
            .created(new URI("/api/order-book-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-book-processes} : get all the orderBookProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderBookProcesss in body.
     */
    @GetMapping("/order-book-processes")
    public List<OrderBookProcessDTO> getAllOrderBookProcesss() {
        log.debug("REST request to get all OrderBookProcesss");
        return orderBookProcessService.findAll();
    }

    /**
     * {@code GET  /order-book-processes/:id} : get the "id" orderBookProcess.
     *
     * @param processInstanceId the id of the orderBookProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderBookProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-book-processes/{processInstanceId}")
    public ResponseEntity<OrderBookProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get OrderBookProcess by processInstanceId : {}", processInstanceId);
        Optional<OrderBookProcessDTO> orderBookProcessDTO = orderBookProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(orderBookProcessDTO);
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OrderBookProcess;
import com.mycompany.myapp.domain.ProcessInstance;
import com.mycompany.myapp.repository.OrderBookProcessRepository;
import com.mycompany.myapp.repository.OrderRepository;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.service.mapper.OrderBookProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderBookProcess}.
 */
@Service
@Transactional
public class OrderBookProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "OrderBookProcess";

    private final Logger log = LoggerFactory.getLogger(OrderBookProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final OrderRepository orderRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final OrderBookProcessMapper orderBookProcessMapper;

    public OrderBookProcessService(
        ProcessInstanceService processInstanceService,
        OrderRepository orderRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        OrderBookProcessMapper orderBookProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.orderRepository = orderRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.orderBookProcessMapper = orderBookProcessMapper;
    }

    /**
     * Save a orderBookProcess.
     *
     * @param orderBookProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderBookProcessDTO create(OrderBookProcessDTO orderBookProcessDTO) {
        log.debug("Request to save OrderBookProcess : {}", orderBookProcessDTO);

        OrderBookProcess orderBookProcess = orderBookProcessMapper.toEntity(orderBookProcessDTO);

        //Saving the domainEntity
        orderRepository.save(orderBookProcess.getOrder());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Order#" + orderBookProcess.getOrder().getId(),
            orderBookProcess
        );
        orderBookProcess.setProcessInstance(processInstance);

        //Saving the process entity
        orderBookProcess = orderBookProcessRepository.save(orderBookProcess);
        return orderBookProcessMapper.toDto(orderBookProcess);
    }

    /**
     * Get all the orderBookProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderBookProcessDTO> findAll() {
        log.debug("Request to get all OrderBookProcesss");
        return orderBookProcessRepository
            .findAll()
            .stream()
            .map(orderBookProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderBookProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderBookProcessDTO> findOne(Long id) {
        log.debug("Request to get OrderBookProcess : {}", id);
        return orderBookProcessRepository.findById(id).map(orderBookProcessMapper::toDto);
    }

    /**
     * Get one orderBookProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderBookProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get OrderBookProcess by  processInstanceId: {}", processInstanceId);
        return orderBookProcessRepository.findByProcessInstanceId(processInstanceId).map(orderBookProcessMapper::toDto);
    }
}

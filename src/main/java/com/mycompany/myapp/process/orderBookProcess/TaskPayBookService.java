package com.mycompany.myapp.process.orderBookProcess;

import com.mycompany.myapp.repository.OrderBookProcessRepository;
import com.mycompany.myapp.repository.TaskInstanceRepository;
import com.mycompany.myapp.service.OrderService;
import com.mycompany.myapp.service.TaskInstanceService;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.service.dto.OrderDTO;
import com.mycompany.myapp.service.dto.TaskInstanceDTO;
import com.mycompany.myapp.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskPayBookService {

    private final TaskInstanceService taskInstanceService;

    private final OrderService orderService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskPayBookMapper taskPayBookMapper;

    public TaskPayBookService(
        TaskInstanceService taskInstanceService,
        OrderService orderService,
        TaskInstanceRepository taskInstanceRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskPayBookMapper taskPayBookMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.orderService = orderService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskPayBookMapper = taskPayBookMapper;
    }

    public TaskPayBookContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        OrderBookProcessDTO orderBookProcess = orderBookProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskPayBookMapper::toOrderBookProcessDTO)
            .orElseThrow();

        TaskPayBookContextDTO taskPayBookContext = new TaskPayBookContextDTO();
        taskPayBookContext.setTaskInstance(taskInstanceDTO);
        taskPayBookContext.setOrderBookProcess(orderBookProcess);

        return taskPayBookContext;
    }

    public TaskPayBookContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskPayBookContextDTO taskPayBookContext) {
        OrderDTO orderDTO = orderService.findOne(taskPayBookContext.getOrderBookProcess().getOrder().getId()).orElseThrow();
        orderDTO.setNumber(taskPayBookContext.getOrderBookProcess().getOrder().getNumber());
        orderDTO.setTotal(taskPayBookContext.getOrderBookProcess().getOrder().getTotal());
        orderDTO.setCcNumber(taskPayBookContext.getOrderBookProcess().getOrder().getCcNumber());
        orderDTO.setCcDate(taskPayBookContext.getOrderBookProcess().getOrder().getCcDate());
        orderDTO.setCcVerifierDigit(taskPayBookContext.getOrderBookProcess().getOrder().getCcVerifierDigit());
        orderService.save(orderDTO);
    }

    public void complete(TaskPayBookContextDTO taskPayBookContext) {
        save(taskPayBookContext);
        taskInstanceService.complete(taskPayBookContext.getTaskInstance(), taskPayBookContext.getOrderBookProcess());
    }
}

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
public class TaskSelectDeliveryService {

    private final TaskInstanceService taskInstanceService;

    private final OrderService orderService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSelectDeliveryMapper taskSelectDeliveryMapper;

    public TaskSelectDeliveryService(
        TaskInstanceService taskInstanceService,
        OrderService orderService,
        TaskInstanceRepository taskInstanceRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSelectDeliveryMapper taskSelectDeliveryMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.orderService = orderService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSelectDeliveryMapper = taskSelectDeliveryMapper;
    }

    public TaskSelectDeliveryContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        OrderBookProcessDTO orderBookProcess = orderBookProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSelectDeliveryMapper::toOrderBookProcessDTO)
            .orElseThrow();

        TaskSelectDeliveryContextDTO taskSelectDeliveryContext = new TaskSelectDeliveryContextDTO();
        taskSelectDeliveryContext.setTaskInstance(taskInstanceDTO);
        taskSelectDeliveryContext.setOrderBookProcess(orderBookProcess);

        return taskSelectDeliveryContext;
    }

    public TaskSelectDeliveryContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSelectDeliveryContextDTO taskSelectDeliveryContext) {
        OrderDTO orderDTO = orderService.findOne(taskSelectDeliveryContext.getOrderBookProcess().getOrder().getId()).orElseThrow();
        orderDTO.setNumber(taskSelectDeliveryContext.getOrderBookProcess().getOrder().getNumber());
        orderDTO.setDeliveryMethod(taskSelectDeliveryContext.getOrderBookProcess().getOrder().getDeliveryMethod());
        orderService.save(orderDTO);
    }

    public void complete(TaskSelectDeliveryContextDTO taskSelectDeliveryContext) {
        save(taskSelectDeliveryContext);
        taskInstanceService.complete(taskSelectDeliveryContext.getTaskInstance(), taskSelectDeliveryContext.getOrderBookProcess());
    }
}

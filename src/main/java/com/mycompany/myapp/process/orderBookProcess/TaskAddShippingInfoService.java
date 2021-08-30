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
public class TaskAddShippingInfoService {

    private final TaskInstanceService taskInstanceService;

    private final OrderService orderService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskAddShippingInfoMapper taskAddShippingInfoMapper;

    public TaskAddShippingInfoService(
        TaskInstanceService taskInstanceService,
        OrderService orderService,
        TaskInstanceRepository taskInstanceRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskAddShippingInfoMapper taskAddShippingInfoMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.orderService = orderService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskAddShippingInfoMapper = taskAddShippingInfoMapper;
    }

    public TaskAddShippingInfoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        OrderBookProcessDTO orderBookProcess = orderBookProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskAddShippingInfoMapper::toOrderBookProcessDTO)
            .orElseThrow();

        TaskAddShippingInfoContextDTO taskAddShippingInfoContext = new TaskAddShippingInfoContextDTO();
        taskAddShippingInfoContext.setTaskInstance(taskInstanceDTO);
        taskAddShippingInfoContext.setOrderBookProcess(orderBookProcess);

        return taskAddShippingInfoContext;
    }

    public TaskAddShippingInfoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskAddShippingInfoContextDTO taskAddShippingInfoContext) {
        OrderDTO orderDTO = orderService.findOne(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getId()).orElseThrow();
        orderDTO.setNumber(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getNumber());
        orderDTO.setStreetAddress(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getStreetAddress());
        orderDTO.setPostalCode(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getPostalCode());
        orderDTO.setCity(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getCity());
        orderDTO.setStateProvince(taskAddShippingInfoContext.getOrderBookProcess().getOrder().getStateProvince());
        orderService.save(orderDTO);
    }

    public void complete(TaskAddShippingInfoContextDTO taskAddShippingInfoContext) {
        save(taskAddShippingInfoContext);
        taskInstanceService.complete(taskAddShippingInfoContext.getTaskInstance(), taskAddShippingInfoContext.getOrderBookProcess());
    }
}

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
public class TaskSelectPickUpStoreService {

    private final TaskInstanceService taskInstanceService;

    private final OrderService orderService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSelectPickUpStoreMapper taskSelectPickUpStoreMapper;

    public TaskSelectPickUpStoreService(
        TaskInstanceService taskInstanceService,
        OrderService orderService,
        TaskInstanceRepository taskInstanceRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSelectPickUpStoreMapper taskSelectPickUpStoreMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.orderService = orderService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSelectPickUpStoreMapper = taskSelectPickUpStoreMapper;
    }

    public TaskSelectPickUpStoreContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        OrderBookProcessDTO orderBookProcess = orderBookProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSelectPickUpStoreMapper::toOrderBookProcessDTO)
            .orElseThrow();

        TaskSelectPickUpStoreContextDTO taskSelectPickUpStoreContext = new TaskSelectPickUpStoreContextDTO();
        taskSelectPickUpStoreContext.setTaskInstance(taskInstanceDTO);
        taskSelectPickUpStoreContext.setOrderBookProcess(orderBookProcess);

        return taskSelectPickUpStoreContext;
    }

    public TaskSelectPickUpStoreContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSelectPickUpStoreContextDTO taskSelectPickUpStoreContext) {
        OrderDTO orderDTO = orderService.findOne(taskSelectPickUpStoreContext.getOrderBookProcess().getOrder().getId()).orElseThrow();
        orderDTO.setNumber(taskSelectPickUpStoreContext.getOrderBookProcess().getOrder().getNumber());
        orderDTO.setStore(taskSelectPickUpStoreContext.getOrderBookProcess().getOrder().getStore());
        orderService.save(orderDTO);
    }

    public void complete(TaskSelectPickUpStoreContextDTO taskSelectPickUpStoreContext) {
        save(taskSelectPickUpStoreContext);
        taskInstanceService.complete(taskSelectPickUpStoreContext.getTaskInstance(), taskSelectPickUpStoreContext.getOrderBookProcess());
    }
}

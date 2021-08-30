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
public class TaskAddBookService {

    private final TaskInstanceService taskInstanceService;

    private final OrderService orderService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final OrderBookProcessRepository orderBookProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskAddBookMapper taskAddBookMapper;

    public TaskAddBookService(
        TaskInstanceService taskInstanceService,
        OrderService orderService,
        TaskInstanceRepository taskInstanceRepository,
        OrderBookProcessRepository orderBookProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskAddBookMapper taskAddBookMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.orderService = orderService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.orderBookProcessRepository = orderBookProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskAddBookMapper = taskAddBookMapper;
    }

    public TaskAddBookContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        OrderBookProcessDTO orderBookProcess = orderBookProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskAddBookMapper::toOrderBookProcessDTO)
            .orElseThrow();

        TaskAddBookContextDTO taskAddBookContext = new TaskAddBookContextDTO();
        taskAddBookContext.setTaskInstance(taskInstanceDTO);
        taskAddBookContext.setOrderBookProcess(orderBookProcess);

        return taskAddBookContext;
    }

    public TaskAddBookContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskAddBookContextDTO taskAddBookContext) {
        OrderDTO orderDTO = orderService.findOne(taskAddBookContext.getOrderBookProcess().getOrder().getId()).orElseThrow();
        orderDTO.setNumber(taskAddBookContext.getOrderBookProcess().getOrder().getNumber());
        orderDTO.setBook(taskAddBookContext.getOrderBookProcess().getOrder().getBook());
        orderService.save(orderDTO);
    }

    public void complete(TaskAddBookContextDTO taskAddBookContext) {
        save(taskAddBookContext);
        taskInstanceService.complete(taskAddBookContext.getTaskInstance(), taskAddBookContext.getOrderBookProcess());
    }
}

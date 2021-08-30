package com.mycompany.myapp.process.orderBookProcess;

import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import com.mycompany.myapp.service.dto.TaskInstanceDTO;

public class TaskAddBookContextDTO {

    private OrderBookProcessDTO orderBookProcess;
    private TaskInstanceDTO taskInstance;

    public OrderBookProcessDTO getOrderBookProcess() {
        return orderBookProcess;
    }

    public void setOrderBookProcess(OrderBookProcessDTO orderBookProcess) {
        this.orderBookProcess = orderBookProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}

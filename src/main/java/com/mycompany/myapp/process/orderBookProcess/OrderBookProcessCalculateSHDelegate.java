package com.mycompany.myapp.process.orderBookProcess;

import com.mycompany.myapp.camunda.CamundaConstants;
import com.mycompany.myapp.domain.Order;
import com.mycompany.myapp.domain.enumeration.DeliveryMethod;
import com.mycompany.myapp.repository.OrderRepository;
import com.mycompany.myapp.service.dto.OrderBookProcessDTO;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderBookProcessCalculateSHDelegate implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(OrderBookProcessCalculateSHDelegate.class);

    private final OrderRepository orderRepository;

    private final RuntimeService runtimeService;

    public OrderBookProcessCalculateSHDelegate(OrderRepository orderRepository, RuntimeService runtimeService) {
        this.orderRepository = orderRepository;
        this.runtimeService = runtimeService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.debug("Inicio de " + OrderBookProcessCalculateSHDelegate.class.getSimpleName());
        OrderBookProcessDTO orderBookProcessDTO = (OrderBookProcessDTO) delegateExecution.getVariable(
                CamundaConstants.PROCESS_INSTANCE
        );

        Order order = orderRepository.findById(orderBookProcessDTO.getOrder().getId()).orElseThrow();

        Integer tax = calculateTax(order);
        Integer deliveryFee = calculateDeliveryFee(order);

        order.setTotal(order.getBook().getPrice().intValue() + tax + deliveryFee);
        orderRepository.save(order);

        orderBookProcessDTO.getOrder().setTotal(order.getTotal());

        runtimeService.setVariable(
                orderBookProcessDTO.getProcessInstance().getCamundaProcessInstanceId(),
                CamundaConstants.PROCESS_INSTANCE,
                orderBookProcessDTO
        );

        runtimeService.setVariable(
                orderBookProcessDTO.getProcessInstance().getCamundaProcessInstanceId(),
                CamundaConstants.PROCESS_INSTANCE_INITIALS,
                orderBookProcessDTO
        );

    }

    private Integer calculateTax(Order order) {
        return order.getBook().getPrice().intValue()/10;
    }

    private Integer calculateDeliveryFee(Order order) {
        if (order.getDeliveryMethod() == DeliveryMethod.PICKUP) {
            return 0;
        }

        return 5;
    }
}

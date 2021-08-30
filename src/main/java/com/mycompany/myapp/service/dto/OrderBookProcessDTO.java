package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OrderBookProcess} entity.
 */
public class OrderBookProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private OrderDTO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderBookProcessDTO)) {
            return false;
        }

        OrderBookProcessDTO orderBookProcessDTO = (OrderBookProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderBookProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderBookProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", order=" + getOrder() +
            "}";
    }
}

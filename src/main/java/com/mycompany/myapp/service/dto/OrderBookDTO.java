package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OrderBook} entity.
 */
public class OrderBookDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer quantity;

    private OrderDTO order;

    private BookDTO book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderBookDTO)) {
            return false;
        }

        OrderBookDTO orderBookDTO = (OrderBookDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderBookDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderBookDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", order=" + getOrder() +
            ", book=" + getBook() +
            "}";
    }
}

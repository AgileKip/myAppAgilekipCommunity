package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OrderBook.
 */
@Entity
@Table(name = "order_book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrderBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "store", "orderBooks" }, allowSetters = true)
    private Order order;

    @ManyToOne
    @JsonIgnoreProperties(value = { "publisher", "authors", "orderBooks" }, allowSetters = true)
    private Book book;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderBook id(Long id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public OrderBook quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return this.order;
    }

    public OrderBook order(Order order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return this.book;
    }

    public OrderBook book(Book book) {
        this.setBook(book);
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderBook)) {
            return false;
        }
        return id != null && id.equals(((OrderBook) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderBook{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}

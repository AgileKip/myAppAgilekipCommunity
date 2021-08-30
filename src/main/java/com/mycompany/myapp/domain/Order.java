package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.DeliveryMethod;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_method")
    private DeliveryMethod deliveryMethod;

    @Column(name = "cc_number")
    private String ccNumber;

    @Column(name = "cc_date")
    private String ccDate;

    @Column(name = "cc_verifier_digit")
    private String ccVerifierDigit;

    @Column(name = "total")
    private Integer total;

    @ManyToOne
    @JsonIgnoreProperties(value = { "publisher", "authors" }, allowSetters = true)
    private Book book;

    @ManyToOne
    private User user;

    @ManyToOne
    private Store store;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order id(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return this.number;
    }

    public Order number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public Order streetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Order postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public Order city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return this.stateProvince;
    }

    public Order stateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public DeliveryMethod getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public Order deliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        return this;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getCcNumber() {
        return this.ccNumber;
    }

    public Order ccNumber(String ccNumber) {
        this.ccNumber = ccNumber;
        return this;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcDate() {
        return this.ccDate;
    }

    public Order ccDate(String ccDate) {
        this.ccDate = ccDate;
        return this;
    }

    public void setCcDate(String ccDate) {
        this.ccDate = ccDate;
    }

    public String getCcVerifierDigit() {
        return this.ccVerifierDigit;
    }

    public Order ccVerifierDigit(String ccVerifierDigit) {
        this.ccVerifierDigit = ccVerifierDigit;
        return this;
    }

    public void setCcVerifierDigit(String ccVerifierDigit) {
        this.ccVerifierDigit = ccVerifierDigit;
    }

    public Integer getTotal() {
        return this.total;
    }

    public Order total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Book getBook() {
        return this.book;
    }

    public Order book(Book book) {
        this.setBook(book);
        return this;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return this.user;
    }

    public Order user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return this.store;
    }

    public Order store(Store store) {
        this.setStore(store);
        return this;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", streetAddress='" + getStreetAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", deliveryMethod='" + getDeliveryMethod() + "'" +
            ", ccNumber='" + getCcNumber() + "'" +
            ", ccDate='" + getCcDate() + "'" +
            ", ccVerifierDigit='" + getCcVerifierDigit() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}

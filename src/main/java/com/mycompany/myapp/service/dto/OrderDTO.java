package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.DeliveryMethod;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Order} entity.
 */
public class OrderDTO implements Serializable {

    private Long id;

    @NotNull
    private String number;

    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    private DeliveryMethod deliveryMethod;

    private String ccNumber;

    private String ccDate;

    private String ccVerifierDigit;

    private Integer total;

    private UserDTO user;

    private StoreDTO store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcDate() {
        return ccDate;
    }

    public void setCcDate(String ccDate) {
        this.ccDate = ccDate;
    }

    public String getCcVerifierDigit() {
        return ccVerifierDigit;
    }

    public void setCcVerifierDigit(String ccVerifierDigit) {
        this.ccVerifierDigit = ccVerifierDigit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDTO)) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderDTO{" +
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
            ", user=" + getUser() +
            ", store=" + getStore() +
            "}";
    }
}

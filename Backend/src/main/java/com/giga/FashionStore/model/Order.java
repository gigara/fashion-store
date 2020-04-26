package com.giga.FashionStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Orders model
 */
@Document(collection = "orders")
public class Order {
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private String order_Id;
    @DBRef
    SiteUser siteUser;
    @DBRef
    private List<OrderProduct> products;
    private String paymentRefID;
    private String deliveryMethod;
    private String address;
    private Date order_timestamp;

    public Order(String order_Id, SiteUser siteUser, List<OrderProduct> products, String paymentRefID, String deliveryMethod, String address, Date order_timestamp) {
        this.order_Id = order_Id;
        this.siteUser = siteUser;
        this.products = products;
        this.paymentRefID = paymentRefID;
        this.deliveryMethod = deliveryMethod;
        this.address = address;
        this.order_timestamp = order_timestamp;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public String getPaymentRefID() {
        return paymentRefID;
    }

    public void setPaymentRefID(String paymentRefID) {
        this.paymentRefID = paymentRefID;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrder_timestamp() {
        return order_timestamp;
    }

    public void setOrder_timestamp(Date order_timestamp) {
        this.order_timestamp = order_timestamp;
    }
}

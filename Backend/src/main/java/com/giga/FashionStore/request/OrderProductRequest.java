package com.giga.FashionStore.request;

public class OrderProductRequest {
    private String prod_Id;
    private int quantity;

    public String getProd_Id() {
        return prod_Id;
    }

    public void setProd_Id(String prod_Id) {
        this.prod_Id = prod_Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

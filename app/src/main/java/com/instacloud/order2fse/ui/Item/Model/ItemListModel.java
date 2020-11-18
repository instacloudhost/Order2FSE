package com.instacloud.order2fse.ui.Item.Model;

public class ItemListModel {
    private String orderId, orderDateTime, orderValue, orderValueText, sellerName, sellerMobile, sellerStatus;

    public ItemListModel(String orderId, String orderDateTime, String orderValue, String orderValueText, String sellerName, String sellerMobile, String sellerStatus) {
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
        this.orderValue = orderValue;
        this.orderValueText = orderValueText;
        this.sellerName = sellerName;
        this.sellerMobile = sellerMobile;
        this.sellerStatus = sellerStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getOrderValueText() {
        return orderValueText;
    }

    public void setOrderValueText(String orderValueText) {
        this.orderValueText = orderValueText;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerMobile() {
        return sellerMobile;
    }

    public void setSellerMobile(String sellerMobile) {
        this.sellerMobile = sellerMobile;
    }

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }
}
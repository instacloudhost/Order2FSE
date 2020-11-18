package com.instacloud.order2fse.ui.AddSeller.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("information")
    @Expose
    private String information;
    @SerializedName("delivery_fee")
    @Expose
    private Integer deliveryFee;
    @SerializedName("default_tax")
    @Expose
    private Integer defaultTax;
    @SerializedName("delivery_range")
    @Expose
    private Integer deliveryRange;
    @SerializedName("available_for_delivery")
    @Expose
    private Boolean availableForDelivery;
    @SerializedName("closed")
    @Expose
    private Object closed;
    @SerializedName("admin_commission")
    @Expose
    private Integer adminCommission;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("has_media")
    @Expose
    private Boolean hasMedia;
    @SerializedName("rate")
    @Expose
    private Object rate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(Integer defaultTax) {
        this.defaultTax = defaultTax;
    }

    public Integer getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Integer deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public Object getClosed() {
        return closed;
    }

    public void setClosed(Object closed) {
        this.closed = closed;
    }

    public Integer getAdminCommission() {
        return adminCommission;
    }

    public void setAdminCommission(Integer adminCommission) {
        this.adminCommission = adminCommission;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

}
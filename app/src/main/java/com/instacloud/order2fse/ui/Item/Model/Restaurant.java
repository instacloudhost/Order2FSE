package com.instacloud.order2fse.ui.Item.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("delivery_fee")
    @Expose
    private Double deliveryFee;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("default_tax")
    @Expose
    private Double defaultTax;
    @SerializedName("available_for_delivery")
    @Expose
    private Boolean availableForDelivery;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields = null;
    @SerializedName("has_media")
    @Expose
    private Boolean hasMedia;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("media")
    @Expose
    private List<Object> media = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(Double defaultTax) {
        this.defaultTax = defaultTax;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public List<Object> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<Object> customFields) {
        this.customFields = customFields;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public List<Object> getMedia() {
        return media;
    }

    public void setMedia(List<Object> media) {
        this.media = media;
    }

}
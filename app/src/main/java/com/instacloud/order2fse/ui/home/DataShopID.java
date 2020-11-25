package com.instacloud.order2fse.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataShopID {


    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("admin_commission")
    @Expose
    private String adminCommission;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("delivery_range")
    @Expose
    private String deliveryRange;
    @SerializedName("default_tax")
    @Expose
    private Integer defaultTax;
    @SerializedName("closed")
    @Expose
    private Boolean closed;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("available_for_delivery")
    @Expose
    private Boolean availableForDelivery;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("custom_field_id")
    @Expose
    private Integer customFieldId;
    @SerializedName("customizable_type")
    @Expose
    private String customizableType;
    @SerializedName("customizable_id")
    @Expose
    private Integer customizableId;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields = null;
    @SerializedName("has_media")
    @Expose
    private Boolean hasMedia;
    @SerializedName("rate")
    @Expose
    private Object rate;
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

    public String getAdminCommission() {
        return adminCommission;
    }

    public void setAdminCommission(String adminCommission) {
        this.adminCommission = adminCommission;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(String deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Integer getDefaultTax() {
        return defaultTax;
    }

    public void setDefaultTax(Integer defaultTax) {
        this.defaultTax = defaultTax;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAvailableForDelivery() {
        return availableForDelivery;
    }

    public void setAvailableForDelivery(Boolean availableForDelivery) {
        this.availableForDelivery = availableForDelivery;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Integer getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Integer customFieldId) {
        this.customFieldId = customFieldId;
    }

    public String getCustomizableType() {
        return customizableType;
    }

    public void setCustomizableType(String customizableType) {
        this.customizableType = customizableType;
    }

    public Integer getCustomizableId() {
        return customizableId;
    }

    public void setCustomizableId(Integer customizableId) {
        this.customizableId = customizableId;
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

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public List<Object> getMedia() {
        return media;
    }

    public void setMedia(List<Object> media) {
        this.media = media;
    }

}

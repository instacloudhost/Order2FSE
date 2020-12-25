package com.instacloud.order2fse.ui.Login.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("stripe_id")
    @Expose
    private Object stripeId;
    @SerializedName("card_brand")
    @Expose
    private Object cardBrand;
    @SerializedName("card_last_four")
    @Expose
    private Object cardLastFour;
    @SerializedName("trial_ends_at")
    @Expose
    private Object trialEndsAt;
    @SerializedName("braintree_id")
    @Expose
    private Object braintreeId;
    @SerializedName("paypal_email")
    @Expose
    private Object paypalEmail;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("custom_fields")
    @Expose
    private CustomFields customFields;
    @SerializedName("has_media")
    @Expose
    private Boolean hasMedia;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Object getStripeId() {
        return stripeId;
    }

    public void setStripeId(Object stripeId) {
        this.stripeId = stripeId;
    }

    public Object getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(Object cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Object getCardLastFour() {
        return cardLastFour;
    }

    public void setCardLastFour(Object cardLastFour) {
        this.cardLastFour = cardLastFour;
    }

    public Object getTrialEndsAt() {
        return trialEndsAt;
    }

    public void setTrialEndsAt(Object trialEndsAt) {
        this.trialEndsAt = trialEndsAt;
    }

    public Object getBraintreeId() {
        return braintreeId;
    }

    public void setBraintreeId(Object braintreeId) {
        this.braintreeId = braintreeId;
    }

    public Object getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(Object paypalEmail) {
        this.paypalEmail = paypalEmail;
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

    public CustomFields getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public List<Object> getMedia() {
        return media;
    }

    public void setMedia(List<Object> media) {
        this.media = media;
    }

}
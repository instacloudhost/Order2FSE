package com.instacloud.order2fse.ui.Payment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("restaurants_id")
    @Expose
    private String restaurantsId;
    @SerializedName("agent_commision")
    @Expose
    private String agentCommision;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRestaurantsId() {
        return restaurantsId;
    }

    public void setRestaurantsId(String restaurantsId) {
        this.restaurantsId = restaurantsId;
    }

    public String getAgentCommision() {
        return agentCommision;
    }

    public void setAgentCommision(String agentCommision) {
        this.agentCommision = agentCommision;
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

    public List<Object> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<Object> customFields) {
        this.customFields = customFields;
    }

}
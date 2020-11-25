package com.instacloud.order2fse.ui.Item.Model.ProductsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomProperties {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("generated_conversions")
    @Expose
    private GeneratedConversions generatedConversions;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public GeneratedConversions getGeneratedConversions() {
        return generatedConversions;
    }

    public void setGeneratedConversions(GeneratedConversions generatedConversions) {
        this.generatedConversions = generatedConversions;
    }

}
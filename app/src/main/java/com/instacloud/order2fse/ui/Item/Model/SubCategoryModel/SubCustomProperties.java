package com.instacloud.order2fse.ui.Item.Model.SubCategoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCustomProperties {
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("generated_conversions")
    @Expose
    private SubGeneratedConversions generatedConversions;

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

    public SubGeneratedConversions getGeneratedConversions() {
        return generatedConversions;
    }

    public void setGeneratedConversions(SubGeneratedConversions generatedConversions) {
        this.generatedConversions = generatedConversions;
    }
}

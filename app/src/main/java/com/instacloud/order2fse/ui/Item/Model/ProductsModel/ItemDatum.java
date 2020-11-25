package com.instacloud.order2fse.ui.Item.Model.ProductsModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("itemcategory_id")
    @Expose
    private Integer itemcategoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("custom_fields")
    @Expose
    private List<Object> customFields = null;
    @SerializedName("has_media")
    @Expose
    private Boolean hasMedia;
    @SerializedName("media")
    @Expose
    private List<ItemMedium> media = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemcategoryId() {
        return itemcategoryId;
    }

    public void setItemcategoryId(Integer itemcategoryId) {
        this.itemcategoryId = itemcategoryId;
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

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public List<ItemMedium> getMedia() {
        return media;
    }

    public void setMedia(List<ItemMedium> media) {
        this.media = media;
    }

}
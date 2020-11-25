package com.instacloud.order2fse.ui.Item.Model.ProductsModel;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemMedium {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("model_type")
    @Expose
    private String modelType;
    @SerializedName("model_id")
    @Expose
    private Integer modelId;
    @SerializedName("collection_name")
    @Expose
    private String collectionName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("mime_type")
    @Expose
    private String mimeType;
    @SerializedName("disk")
    @Expose
    private String disk;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("manipulations")
    @Expose
    private List<Object> manipulations = null;
    @SerializedName("custom_properties")
    @Expose
    private CustomProperties customProperties;
    @SerializedName("responsive_images")
    @Expose
    private List<Object> responsiveImages = null;
    @SerializedName("order_column")
    @Expose
    private Integer orderColumn;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("formated_size")
    @Expose
    private String formatedSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Object> getManipulations() {
        return manipulations;
    }

    public void setManipulations(List<Object> manipulations) {
        this.manipulations = manipulations;
    }

    public CustomProperties getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public List<Object> getResponsiveImages() {
        return responsiveImages;
    }

    public void setResponsiveImages(List<Object> responsiveImages) {
        this.responsiveImages = responsiveImages;
    }

    public Integer getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(Integer orderColumn) {
        this.orderColumn = orderColumn;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFormatedSize() {
        return formatedSize;
    }

    public void setFormatedSize(String formatedSize) {
        this.formatedSize = formatedSize;
    }

}
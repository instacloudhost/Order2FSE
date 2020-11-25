package com.instacloud.order2fse.ui.Item.Model.SubCategoryModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubGeneratedConversions {

    @SerializedName("thumb")
    @Expose
    private Boolean thumb;
    @SerializedName("icon")
    @Expose
    private Boolean icon;

    public Boolean getThumb() {
        return thumb;
    }

    public void setThumb(Boolean thumb) {
        this.thumb = thumb;
    }

    public Boolean getIcon() {
        return icon;
    }

    public void setIcon(Boolean icon) {
        this.icon = icon;
    }
}

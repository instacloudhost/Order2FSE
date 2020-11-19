package com.instacloud.order2fse.ui.Item.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAddMenu {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("discount_price")
    @Expose
    private String discountPrice;

    @SerializedName("id")
    @Expose
    private String id;

    public DataAddMenu(String name, String price, String discountPrice, String id) {
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

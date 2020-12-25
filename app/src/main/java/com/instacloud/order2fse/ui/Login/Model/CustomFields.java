package com.instacloud.order2fse.ui.Login.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomFields {

    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("bio")
    @Expose
    private Bio bio;
    @SerializedName("address")
    @Expose
    private Address address;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Bio getBio() {
        return bio;
    }

    public void setBio(Bio bio) {
        this.bio = bio;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
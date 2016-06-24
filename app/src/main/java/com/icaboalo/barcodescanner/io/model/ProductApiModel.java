package com.icaboalo.barcodescanner.io.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by icaboalo on 21/06/16.
 */
public class ProductApiModel {

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("trademark")
    private String trademark;

    @SerializedName("type")
    private String type;

    private String code;

    private int quantity = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

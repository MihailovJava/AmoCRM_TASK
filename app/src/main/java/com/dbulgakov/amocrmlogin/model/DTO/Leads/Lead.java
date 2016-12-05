package com.dbulgakov.amocrmlogin.model.DTO.Leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lead {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date_create")
    @Expose
    private int dateCreate;

    @SerializedName("price")
    @Expose
    private int price;

    public String getName() {
        return name;
    }

    public int getDateSeconds() {
        return dateCreate;
    }

    public int getPrice() {
        return price;
    }
}

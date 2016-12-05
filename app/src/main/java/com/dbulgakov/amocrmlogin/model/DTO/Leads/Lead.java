package com.dbulgakov.amocrmlogin.model.DTO.Leads;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lead implements Comparable<Lead>, Serializable{

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date_create")
    @Expose
    private long dateCreate;

    @SerializedName("price")
    @Expose
    private long price;

    public String getName() {
        return name;
    }

    public long getDateSeconds() {
        return dateCreate;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public int compareTo(@NonNull Lead lead) {
        return Long.valueOf(dateCreate).compareTo(lead.getDateSeconds());
    }
}

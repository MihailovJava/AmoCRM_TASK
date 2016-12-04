package com.dbulgakov.amocrmlogin.model.DTO.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountInfoResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("subdomain")
    @Expose
    private String domain;

    public String getDomainName() {
        return domain;
    }

    public String getName() {
        return name;
    }
}

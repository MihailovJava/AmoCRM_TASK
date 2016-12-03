package com.dbulgakov.amocrmlogin.model.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponseInfo {

    @SerializedName("auth")
    @Expose
    private boolean auth;

    @SerializedName("user_api_key")
    @Expose
    private String apiKey;

    @SerializedName("error")
    @Expose
    private String error;


    @SerializedName("accounts")
    @Expose
    private List<AccountInfoResponse> accounts;

    public boolean isAuthCompleted() {
        return auth;
    }

    public String getApiKey() {
        return apiKey;
    }

    public List<AccountInfoResponse> getAccountsList() {
        return accounts;
    }

    public String getError() { return error; }
}

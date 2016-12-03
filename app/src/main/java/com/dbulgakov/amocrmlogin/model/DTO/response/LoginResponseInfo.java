package com.dbulgakov.amocrmlogin.model.DTO.response;

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

    @SerializedName("accounts")
    @Expose
    private List<AccountInfoResponse> accounts;

    public boolean isAuthed() {
        return auth;
    }

    public String getApiKey() {
        return apiKey;
    }

    public List<AccountInfoResponse> getAccountsList() {
        return accounts;
    }
}

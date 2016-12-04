package com.dbulgakov.amocrmlogin.model.DTO.Leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeadsResponse {

    @SerializedName("response")
    @Expose
    private LeadsInfoResponse leadsInfoResponse;

    public LeadsInfoResponse getLeadsInfoResponse() {
        return leadsInfoResponse;
    }
}

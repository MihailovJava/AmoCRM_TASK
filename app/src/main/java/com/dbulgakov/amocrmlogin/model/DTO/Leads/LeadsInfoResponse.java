package com.dbulgakov.amocrmlogin.model.DTO.Leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeadsInfoResponse {

    @SerializedName("leads")
    @Expose
    private List<Lead> leadList;

    public List<Lead> getLeadList() {
        return leadList;
    }
}

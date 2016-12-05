package com.dbulgakov.amocrmlogin.view;

import com.dbulgakov.amocrmlogin.model.DTO.Leads.Lead;

import java.util.List;

public interface MainView extends View {
    void startLoginActivity();
    void addLeads(List<Lead> leadList);
    List<Lead> getLeads();
}

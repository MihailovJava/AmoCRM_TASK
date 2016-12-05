package com.dbulgakov.amocrmlogin.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.model.DTO.Leads.Lead;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadListAdapter extends RecyclerView.Adapter<LeadListAdapter.ViewHolder>{
    private List<Lead> leadList;

    public LeadListAdapter(List<Lead> leadList) {
        this.leadList = leadList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lead_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lead lead = leadList.get(position);
        setTextIntoTextViews(lead, holder);
    }

    @Override
    public int getItemCount() {
        return leadList.size();
    }

    public void setLeadList(List<Lead> leadList) {
        this.leadList = leadList;
        notifyDataSetChanged();
    }

    private void setTextIntoTextViews(Lead lead, ViewHolder holder){
        holder.leadNameTextView.setText(lead.getName());
        holder.leadPriceTextView.setText(String.valueOf(lead.getPrice()));
        holder.leadDateTextView.setText(String.valueOf(lead.getDateCreate()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lead_name_textview)
        TextView leadNameTextView;

        @BindView(R.id.lead_price_textview)
        TextView leadPriceTextView;

        @BindView(R.id.lead_date_textview)
        TextView leadDateTextView;

        @BindView(R.id.lead_status_textview)
        TextView leadStatusTextView;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}

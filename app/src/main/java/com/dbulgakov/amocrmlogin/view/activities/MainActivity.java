package com.dbulgakov.amocrmlogin.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.model.DTO.Leads.Lead;
import com.dbulgakov.amocrmlogin.other.di.view.DaggerViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewDynamicModule;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.presenter.MainPresenter;
import com.dbulgakov.amocrmlogin.view.MainView;
import com.dbulgakov.amocrmlogin.view.adapters.LeadListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dbulgakov.amocrmlogin.other.App.getContext;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener{

    private ViewComponent viewComponent;

    @Inject
    protected MainPresenter mainPresenter;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.leads_recycler_view)
    RecyclerView leadsRecyclerView;

    private LeadListAdapter leadListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerView();
        mainPresenter.checkAuth();
    }

    @Override
    public void startLoginActivity() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void addLeads(List<Lead> leadList) {
        leadListAdapter.setLeadList(leadList);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void startProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void stopProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mainPresenter.getUserLeads();
    }

    private void initRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        leadsRecyclerView.setLayoutManager(llm);
        leadListAdapter = new LeadListAdapter(new ArrayList<>());
        leadsRecyclerView.setAdapter(leadListAdapter);
    }
}

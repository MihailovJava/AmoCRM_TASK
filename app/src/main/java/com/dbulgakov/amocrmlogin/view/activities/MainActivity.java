package com.dbulgakov.amocrmlogin.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.other.di.view.DaggerViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewDynamicModule;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.presenter.MainPresenter;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener{

    private ViewComponent viewComponent;

    @Inject
    protected MainPresenter mainPresenter;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

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
        mainPresenter.checkAuth();
    }

    @Override
    public void startLoginActivity() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
}

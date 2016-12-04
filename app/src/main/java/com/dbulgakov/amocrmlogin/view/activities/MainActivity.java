package com.dbulgakov.amocrmlogin.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.other.di.view.DaggerViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewDynamicModule;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.presenter.MainPresenter;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView{

    private ViewComponent viewComponent;

    @Inject
    protected MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        mainPresenter.checkAuth();
        setContentView(R.layout.activity_main);
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
}

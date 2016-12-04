package com.dbulgakov.amocrmlogin.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dbulgakov.amocrmlogin.R;
import com.dbulgakov.amocrmlogin.other.di.view.DaggerViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewComponent;
import com.dbulgakov.amocrmlogin.other.di.view.ViewDynamicModule;
import com.dbulgakov.amocrmlogin.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView{

    private ViewComponent viewComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
    }

    @Override
    public void showError(Throwable throwable) {

    }
}

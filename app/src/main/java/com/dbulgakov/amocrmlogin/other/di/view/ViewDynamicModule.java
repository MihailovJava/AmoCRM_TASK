package com.dbulgakov.amocrmlogin.other.di.view;

import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.presenter.MainPresenter;
import com.dbulgakov.amocrmlogin.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {
    private final MainView view;

    public ViewDynamicModule(MainView view) {
        this.view = view;
    }

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenter(view);
    }
}

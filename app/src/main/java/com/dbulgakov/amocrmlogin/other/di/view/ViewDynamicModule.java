package com.dbulgakov.amocrmlogin.other.di.view;

import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.view.LoginView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {
    private final LoginView view;

    public ViewDynamicModule(LoginView view) {
        this.view = view;
    }

    @Provides
    LoginPresenter provideMainPresenter() {
        return new LoginPresenter(view);
    }
}

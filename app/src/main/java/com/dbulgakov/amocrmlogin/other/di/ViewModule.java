package com.dbulgakov.amocrmlogin.other.di;

import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}

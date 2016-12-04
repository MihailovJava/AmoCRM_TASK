package com.dbulgakov.amocrmlogin.other.di;

import android.accounts.AccountManager;

import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}

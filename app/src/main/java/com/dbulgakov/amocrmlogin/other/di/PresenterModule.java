package com.dbulgakov.amocrmlogin.other.di;

import android.accounts.AccountManager;

import com.dbulgakov.amocrmlogin.model.Model;
import com.dbulgakov.amocrmlogin.model.ModelImpl;
import com.dbulgakov.amocrmlogin.other.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    @Singleton
    AccountManager provideAccountManagerRepository() {
        return AccountManager.get(App.getContext());
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}

package com.dbulgakov.amocrmlogin.presenter;

import android.accounts.AccountManager;
import android.util.Log;

import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;

public class MainPresenter extends BasePresenter {

    private final MainView mainView;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    @Inject
    AccountManager accountManager;

    @Inject
    public MainPresenter(MainView mainView) {
        super();
        App.getComponent().inject(this);
        this.mainView = mainView;
    }

    @SuppressWarnings("MissingPermission")
    public void checkAuth() {
        if (accountManager.getAccountsByType(Const.ACCOUNT_TYPE).length < 1) {
            mainView.startLoginActivity();
        }
    }
}

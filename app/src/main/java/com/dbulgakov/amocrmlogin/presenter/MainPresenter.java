package com.dbulgakov.amocrmlogin.presenter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;

public class MainPresenter extends BasePresenter {

    private final MainView mainView;
    private String userDomain;
    private String userEmail;
    private String userApiKey;

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

    public void checkAuth() {
        if (!hasLoggedAccount()) {
            startLoginActivity();
        } else {
            getUserCredentials();
        }
    }

    @SuppressWarnings("MissingPermission")
    private boolean hasLoggedAccount(){
        return !(accountManager.getAccountsByType(Const.ACCOUNT_TYPE).length < 1);
    }

    private void startLoginActivity(){
        mainView.startLoginActivity();
    }

    @SuppressWarnings("MissingPermission")
    private void getUserCredentials() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Account[] accs = accountManager.getAccountsByType(Const.ACCOUNT_TYPE);
                    Bundle bnd = accountManager.getAuthToken(accs[0], AccountManager.KEY_AUTHTOKEN, null, null, null, null).getResult();
                    userDomain = accountManager.getUserData(accs[0], Const.USER_ACCOUNT_DOMAIN_KEY);
                    userApiKey = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                    userEmail = bnd.getString(AccountManager.KEY_ACCOUNT_NAME);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }

                return null;
            }
        };
        task.execute();
    }
}

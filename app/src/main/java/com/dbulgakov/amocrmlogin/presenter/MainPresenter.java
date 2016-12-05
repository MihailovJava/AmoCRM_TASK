package com.dbulgakov.amocrmlogin.presenter;

import android.accounts.AccountManager;
import android.util.Log;

import com.dbulgakov.amocrmlogin.model.DTO.Leads.Lead;
import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

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
            getUserLeads();
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
    public void getUserLeads() {
        mainView.startProgressBar();
        if (credentialsSet()) {
            getLeadsDirectly();
        } else {
            getLeadsAndCredentials();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getLeadsAndCredentials() {
        Subscription subscription = Observable.just(accountManager.getAccountsByType(Const.ACCOUNT_TYPE))
                .map(accs -> {
                    try {
                        userDomain = accountManager.getUserData(accs[0], Const.USER_ACCOUNT_DOMAIN_KEY);
                        return accountManager.getAuthToken(accs[0], AccountManager.KEY_AUTHTOKEN, null, null, null, null).getResult();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .subscribe(bundle -> {
                    userEmail = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                    userApiKey = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                    getLeadsDirectly();
                }, (throwable) -> {
                    mainView.stopProgressBar();
                    throwable.printStackTrace();
                });
        addSubscription(subscription);
    }

    private void getLeadsDirectly(){
        model.getLeads(userEmail, userApiKey, userDomain)
                .map(leadsResponse -> leadsResponse.getLeadsInfoResponse().getLeadList())
                .flatMap(Observable::from)
                .toSortedList(Lead::compareTo)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .subscribe(leads -> {
                    mainView.stopProgressBar();
                    mainView.addLeads(leads);
                }, (throwable) -> {
                    mainView.stopProgressBar();
                    throwable.printStackTrace();
                });
    }


    private boolean credentialsSet(){
        return userEmail != null && userApiKey != null && userDomain != null;
    }
}

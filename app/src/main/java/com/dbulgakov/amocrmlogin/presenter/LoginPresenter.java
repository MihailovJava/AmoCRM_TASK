package com.dbulgakov.amocrmlogin.presenter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.text.TextUtils;

import com.dbulgakov.amocrmlogin.model.DTO.Login.LoginResponse;
import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;
import com.dbulgakov.amocrmlogin.view.LoginView;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Scheduler;
import rx.Subscription;

public class LoginPresenter extends BasePresenter{
    private LoginView loginView;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    @Inject
    AccountManager accountManager;

    public void onCreate(LoginView loginView) {
        this.loginView = loginView;
        App.getComponent().inject(this);
    }

    public void onLoginButtonClick(String userEmail, String userPassword) {
        loginView.startProgressBar();
        Subscription subscription = model.performAuth(userEmail, userPassword)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .subscribe(loginResponse -> {
                    loginView.stopProgressBar();
                    if (loginResponse.getLoginResponseInfo().isAuthCompleted()) {
                        saveCredentials(userEmail, loginResponse);
                        loginView.startMainActivity();
                    } else {
                        throw new IllegalArgumentException("wrong username or password");
                    }

                }, throwable -> {
                    loginView.stopProgressBar();
                    loginView.showError(throwable);
                    throwable.printStackTrace();
                });
        addSubscription(subscription);
    }

    public boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= Const.MIN_PASS_LENGTH;
    }

    @SuppressWarnings("MissingPermission")
    private void saveCredentials(String userEmail, LoginResponse loginResponse) {
        Account account = new Account(userEmail, Const.ACCOUNT_TYPE);
        accountManager.addAccountExplicitly(account, loginResponse.getLoginResponseInfo().getApiKey(), null);
        accountManager.setUserData(account, Const.USER_ACCOUNT_DOMAIN_KEY,
                loginResponse.getLoginResponseInfo().getAccountsList().get(0).getDomainName());
        // believe that there is one account
        accountManager.setAuthToken(account, AccountManager.KEY_AUTHTOKEN, loginResponse.getLoginResponseInfo().getApiKey());
    }
}

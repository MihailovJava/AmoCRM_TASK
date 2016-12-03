package com.dbulgakov.amocrmlogin.presenter;

import android.text.TextUtils;
import android.util.Log;

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
    public LoginPresenter(LoginView loginView) {
        super();
        App.getComponent().inject(this);
        this.loginView = loginView;
    }

    public void onLoginButtonClick(String userEmail, String userPassword) {
        loginView.startProgressBar();
        Subscription subscription = model.performAuth(userEmail, userPassword)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .subscribe(loginResponse -> {
                    loginView.startMainActivity();

                }, throwable -> {
                    loginView.showError(throwable);
                    throwable.printStackTrace();
                }, () -> {
                    loginView.stopProgressBar();
                });
        addSubscription(subscription);
    }

    public boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= Const.MIN_PASS_LENGTH;
    }
}

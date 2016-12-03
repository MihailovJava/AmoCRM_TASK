package com.dbulgakov.amocrmlogin.model;

import com.dbulgakov.amocrmlogin.model.DTO.request.LoginRequest;
import com.dbulgakov.amocrmlogin.model.DTO.response.LoginResponse;
import com.dbulgakov.amocrmlogin.model.api.ApiInterface;
import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    ApiInterface apiInterface;

    @Inject
    Gson gson;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread);
    }

    @Override
    public Observable<LoginResponse> performAuth(String username, String userPassword) {
        return apiInterface.authUser(new LoginRequest(username, userPassword), Const.RESPONSE_TYPE);
    }
}

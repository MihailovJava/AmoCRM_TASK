package com.dbulgakov.amocrmlogin.model;

import com.dbulgakov.amocrmlogin.model.DTO.LoginResponse;
import com.dbulgakov.amocrmlogin.model.api.ApiInterface;
import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.other.Const;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    ApiInterface apiInterface;

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
    public Observable<LoginResponse> performAuth(String userEmail, String userPassword) {
        return apiInterface
                .authUser(userEmail, userPassword, Const.RESPONSE_TYPE)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}

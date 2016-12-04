package com.dbulgakov.amocrmlogin.model;

import android.util.Log;

import com.dbulgakov.amocrmlogin.model.DTO.Leads.LeadsResponse;
import com.dbulgakov.amocrmlogin.model.DTO.Login.LoginResponse;
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

    @Override
    public Observable<LeadsResponse> getLeads(String userEmail, String userApiKey, String userDomain) {
        return apiInterface
                .getLeads(createGetLeadsUrl(userEmail, userApiKey, userDomain))
                .compose(applySchedulers());
    }

    private String createGetLeadsUrl(String userEmail, String userApiKey, String userDomain) {
        return String.format(Const.GET_LEADS_STRING, userDomain, userEmail, userApiKey);
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}

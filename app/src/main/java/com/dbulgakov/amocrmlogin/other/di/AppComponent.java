package com.dbulgakov.amocrmlogin.other.di;

import com.dbulgakov.amocrmlogin.model.ModelImpl;
import com.dbulgakov.amocrmlogin.presenter.BasePresenter;
import com.dbulgakov.amocrmlogin.presenter.LoginPresenter;
import com.dbulgakov.amocrmlogin.presenter.MainPresenter;
import com.dbulgakov.amocrmlogin.view.activities.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(ModelImpl dataRepository);
    void inject(BasePresenter basePresenter);
    void inject(LoginPresenter loginPresenter);
    void inject(MainPresenter mainPresenter);
    void inject(LoginActivity loginActivity);
}

package com.dbulgakov.amocrmlogin.other.di;

import com.dbulgakov.amocrmlogin.model.ModelImpl;
import com.dbulgakov.amocrmlogin.presenter.BasePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {
    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);
}

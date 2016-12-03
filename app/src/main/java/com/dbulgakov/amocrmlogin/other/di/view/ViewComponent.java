package com.dbulgakov.amocrmlogin.other.di.view;

import com.dbulgakov.amocrmlogin.view.activities.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {
    void inject(LoginActivity loginActivity);
}

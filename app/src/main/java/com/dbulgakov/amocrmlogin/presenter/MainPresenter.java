package com.dbulgakov.amocrmlogin.presenter;

import com.dbulgakov.amocrmlogin.other.App;
import com.dbulgakov.amocrmlogin.view.MainView;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter {

    private final MainView mainView;

    @Inject
    public MainPresenter(MainView mainView) {
        super();
        App.getComponent().inject(this);
        this.mainView = mainView;
    }
}

package com.dbulgakov.amocrmlogin.model;

import com.dbulgakov.amocrmlogin.model.DTO.Login.LoginResponse;

import rx.Observable;

public interface Model {
    Observable<LoginResponse> performAuth(String username, String password);
}

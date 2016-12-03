package com.dbulgakov.amocrmlogin.model.api;

import com.dbulgakov.amocrmlogin.model.DTO.request.LoginRequest;
import com.dbulgakov.amocrmlogin.model.DTO.response.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {
    @POST("account/api_auth.php")
    Observable<LoginResponse> authUser(@Body LoginRequest loginRequest, @Query("type") String answerType);
}

package com.example.user.api;

import com.example.user.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("getUser.php")
    Call<ResponseModel> getUser();
}

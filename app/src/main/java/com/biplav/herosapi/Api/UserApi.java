package com.biplav.herosapi.Api;

import com.biplav.herosapi.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    //create user
    @POST("users/signup")
    Call<Void> registerUser(@Body User user);

    //login user
    @POST("users/login")
    Call<Void> loginUser(@Body User user);

}

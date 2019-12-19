package com.biplav.herosapi.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    public static final String base_url="http://10.0.2.2:3001/";


        //@FormUrlEncoded
    //  @POST("users/login")

    //@FormUrlEncoded
    //  @POST("users/signup")

    //instance of Retrofit
    public static Retrofit getInstance()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

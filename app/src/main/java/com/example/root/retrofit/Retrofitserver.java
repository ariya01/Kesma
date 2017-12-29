package com.example.root.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 12/26/17.
 */

public class Retrofitserver {
    public static final String url = "http://10.151.12.253/android/";

    private static Retrofit retrofit;

    public static Retrofit getclient()
    {
        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

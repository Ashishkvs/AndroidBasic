package com.imagegrafia.androidbasic.api;

import com.imagegrafia.androidbasic.AppConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceConfig {
//    HttpLoInterceptor interceptor = HttpLogg
    //Logger Interceptor
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
//        .addInterceptor(null);

    public static Retrofit retroFitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build())
                .build();
    }
}

package com.imagegrafia.androidbasic.api;

import com.imagegrafia.androidbasic.adapter.TaskItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApiService {
    @POST("user/login")
    Call<Object> userLogin(@Body User user);

    @POST("user/signup")
    Call<Object> userSignup(@Body User user);

//    @GET("order/temp/{orderNumber}")
//    Call<List<TaskItem>> getOderItem(@Header("Authorization") String authorization, @Path("orderNumber") Integer orderNumber);
}

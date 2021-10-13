package com.imagegrafia.androidbasic.api;

import com.imagegrafia.androidbasic.adapter.TaskItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DeliveryApiService {
    @GET("order/{orderNumber}")
    Call<Object> getOrder(@Header("Authorization") String authorization, @Path("orderNumber") Integer orderNumber);

    @GET("order/temp/{orderNumber}")
    Call<List<TaskItem>> getOderItem(@Header("Authorization") String authorization, @Path("orderNumber") Integer orderNumber);
}

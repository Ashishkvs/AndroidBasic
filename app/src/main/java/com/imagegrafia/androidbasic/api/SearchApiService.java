package com.imagegrafia.androidbasic.api;

import com.imagegrafia.androidbasic.search.SearchItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchApiService {
    @GET("restaurent/4/item/search/testing")
    Call<List<SearchItem>> searchItem(@Header("Authorization") String authorization,@Query("q") String q);

}

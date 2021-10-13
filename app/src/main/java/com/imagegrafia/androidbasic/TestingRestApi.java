package com.imagegrafia.androidbasic;

import android.os.Bundle;
//import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.imagegrafia.androidbasic.api.DeliveryApiService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestingRestApi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_rest_api);

        final TextView textView = findViewById(R.id.textView2);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7777")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DeliveryApiService dashDeliveryApiService = retrofit.create(DeliveryApiService.class);
        String authorization = "Basic YXNoaXNoa3ZzQGdtYWlsLmNvbTowMDlpbnNwaXJlZA==";
        Gson gson = new Gson();
        Call<Object> call = dashDeliveryApiService.getOrder(authorization,4);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                textView.setText(gson.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                textView.setText("api call failed"+t.getLocalizedMessage());
            }
        });

    }
}
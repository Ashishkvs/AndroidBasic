package com.imagegrafia.androidbasic;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imagegrafia.androidbasic.adapter.TaskAdapter;
import com.imagegrafia.androidbasic.adapter.TaskItem;
import com.imagegrafia.androidbasic.api.DeliveryApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LiveTaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
//    List<TaskItem> taskItemList = new ArrayList<>();
    //smart way
//        Stream.iterate(1,i -> i+1 ).limit(10).forEach(data -> taskItemList.add(new TaskItem("Title"+data, data.toString(), "https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_960_720.jpg")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_task);

        recyclerView = findViewById(R.id.liveTaskRecyclerView);

        fetchLiveTask();
    }

    private void fetchLiveTask() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:7777")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DeliveryApiService dashDeliveryApiService = retrofit.create(DeliveryApiService.class);
        String authorization = "Basic YXNoaXNoa3ZzQGdtYWlsLmNvbTowMDlpbnNwaXJlZA==";
        Call<List<TaskItem>> call = dashDeliveryApiService.getOderItem(authorization, 4);
        call.enqueue(new Callback<List<TaskItem>>() {
            @Override
            public void onResponse(Call<List<TaskItem>> call, Response<List<TaskItem>> response) {
                setDataIntoRecyclerView(response.body());
                Toast.makeText(LiveTaskActivity.this, "Success fetching record", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<TaskItem>> call, Throwable t) {
                Toast.makeText(LiveTaskActivity.this, "Failed fetching record", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDataIntoRecyclerView(List<TaskItem> taskItemList) {
        Log.i("setDataIntoRecyclerView : ", "size :" + taskItemList.size());
        TaskAdapter taskAdapter = new TaskAdapter(LiveTaskActivity.this, taskItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(LiveTaskActivity.this));
        recyclerView.setAdapter(taskAdapter);
    }
}
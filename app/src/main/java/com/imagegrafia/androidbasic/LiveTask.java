package com.imagegrafia.androidbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.imagegrafia.androidbasic.adapter.TaskAdapter;
import com.imagegrafia.androidbasic.adapter.TaskItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LiveTask extends AppCompatActivity {

    RecyclerView recyclerView;
    List<TaskItem> taskItemList = new ArrayList<>();
    {
        taskItemList.add(new TaskItem("Pixbay","1","https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_960_720.jpg"));
        taskItemList.add(new TaskItem("Pixbaysdf","2","https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539__480.jpg"));
        taskItemList.add(new TaskItem("Pixbaydsf ","3","https://cdn.pixabay.com/photo/2021/09/17/08/40/lake-6632026__340.jpg"));
        taskItemList.add(new TaskItem("Pixbay dfsdf","4","https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_960_720.jpg"));
        taskItemList.add(new TaskItem("Pixbay ad","5","https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539__480.jpg"));
        taskItemList.add(new TaskItem("Pixbay aaaa","6","https://cdn.pixabay.com/photo/2021/09/17/08/40/lake-6632026__340.jpg"));
        taskItemList.add(new TaskItem("Pixbay ssss" ,"7","https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_960_720.jpg"));
        taskItemList.add(new TaskItem("Pixbay  www","8","https://cdn.pixabay.com/photo/2017/02/01/22/02/mountain-landscape-2031539__480.jpg"));
        taskItemList.add(new TaskItem("Pixbay we","9","https://cdn.pixabay.com/photo/2021/09/17/08/40/lake-6632026__340.jpg"));

        //smart way
        Stream.iterate(1,i -> i+1 ).limit(10).forEach(data -> taskItemList.add(new TaskItem("Title"+data, data.toString(), "https://cdn.pixabay.com/photo/2020/09/19/19/37/landscape-5585247_960_720.jpg")));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_task);

        recyclerView = findViewById(R.id.liveTaskRecyclerView);
        TaskAdapter taskAdapter = new TaskAdapter(this,this.taskItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }
}
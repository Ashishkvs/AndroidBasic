package com.imagegrafia.androidbasic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imagegrafia.androidbasic.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context mContext;
    private List<TaskItem> taskItemList;

    public TaskAdapter(Context mContext, List<TaskItem> taskItemList) {
        this.mContext = mContext;
        this.taskItemList = taskItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.task_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(taskItemList.get(position).getId());
        holder.title.setText(taskItemList.get(position).getTitle());

        //Adding glide library to display images
        Glide.with(mContext).load(taskItemList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return taskItemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView id;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textTitle);
            id = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView2);

        }
    }
}

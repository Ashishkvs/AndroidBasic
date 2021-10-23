package com.imagegrafia.androidbasic.search;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchItemViewHolder> {
    private List<SearchItem> searchItemList;
    private Context mContext;

    public SearchAdapter(Context mContext, List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.search_item, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        holder.description.setText(searchItemList.get(position).getDescription());
        holder.title.setText(searchItemList.get(position).getTitle());
        //Adding glide library to display images
        Glide.with(mContext).load(searchItemList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
    }

    public static class SearchItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView imageView;

        public SearchItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtViewSearchTitle);
            description = itemView.findViewById(R.id.txtViewSearchDesc);
            imageView = itemView.findViewById(R.id.searchItemImg);
        }
    }
}

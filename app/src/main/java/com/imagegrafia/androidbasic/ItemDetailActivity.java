package com.imagegrafia.androidbasic;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.imagegrafia.androidbasic.search.SearchItem;

import java.io.Serializable;

public class ItemDetailActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ImageView imageView = findViewById(R.id.itemImage);
        TextView itemDesc = findViewById(R.id.itemDesc);
        TextView itemInfo = findViewById(R.id.itemInfo);
        TextView itemName = findViewById(R.id.itemName);
        SearchItem searchItem = (SearchItem) getIntent().getSerializableExtra(AppConstant.SEARCH_ITEM_DETAIL);
        Log.i(TAG, "ITEM : "+searchItem.toString());

        Glide.with(this).load(searchItem.getImageUrl()).into(imageView);
        itemName.setText(searchItem.getTitle());
        itemDesc.setText(searchItem.getDescription());
        itemInfo.setText("Some extra info if required");
    }
}
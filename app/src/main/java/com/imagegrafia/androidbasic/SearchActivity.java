package com.imagegrafia.androidbasic;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imagegrafia.androidbasic.api.SearchApiService;
import com.imagegrafia.androidbasic.search.SearchAdapter;
import com.imagegrafia.androidbasic.search.SearchItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.searchItemRecyclerView);
        EditText editSearchText = findViewById(R.id.editSearchText);
//        Intent intent = getIntent();
//        String authorization = intent.getStringExtra(AppConstant.AUTHORIZATION);
//        editSearchText.onChna

        TextWatcher searchTextWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged");
//                searchText(editSearchText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged");
                searchText(editSearchText.getText().toString());
            }
        };
        editSearchText.addTextChangedListener(searchTextWatcher);
    }

    private void searchText(String searchText) {
        Log.i(TAG, "searchText : " + searchText);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SearchApiService searchApiService = retrofit.create(SearchApiService.class);
        //TODO remove auth from search
        String auth = "Basic YXNoaXNoa3ZzQGdtYWlsLmNvbTowMDlpbnNwaXJlZA==";
        Call<List<SearchItem>> call = searchApiService.searchItem(auth, searchText);
        call.enqueue(new Callback<List<SearchItem>>() {
            @Override
            public void onResponse(Call<List<SearchItem>> call, Response<List<SearchItem>> response) {
                Log.i(TAG, response.toString());
//                if (response.body() != null && !response.body().isEmpty()) {
                setDataIntoRecyclerView(response.body());
//                }
            }

            @Override
            public void onFailure(Call<List<SearchItem>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Failed while making api call", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDataIntoRecyclerView(List<SearchItem> searchItemList) {
        Log.i("setDataIntoRecyclerView : ", "size :" + searchItemList.size());
        if (searchItemList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);

            SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, searchItemList);
            recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
            recyclerView.setAdapter(searchAdapter);
        }

    }
}
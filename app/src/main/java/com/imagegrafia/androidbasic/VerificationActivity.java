package com.imagegrafia.androidbasic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imagegrafia.androidbasic.api.LoginApiService;
import com.imagegrafia.androidbasic.service.AppConstant;
import com.imagegrafia.androidbasic.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerificationActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    TextView txtViewTokenErrMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Intent intent = getIntent();
        String authorization = intent.getStringExtra(AppConstant.AUTHORIZATION);
        Log.i(TAG, "intent authToken : " + authorization);

        EditText editTextToken = findViewById(R.id.editTextToken);
        txtViewTokenErrMessage = findViewById(R.id.txtViewTokenErrMessage);
        Button btnVerify = findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(v -> userTokenVerification(editTextToken.getText().toString()));
    }

    private void userTokenVerification(String token) {
        Log.i(TAG, "verification token :" + token);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);

        Call<Object> call = loginApiService.userTokenVerification(token);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i(TAG, response.toString());
                if (response.code() == 200) {
                    Toast.makeText(VerificationActivity.this, "Account Verified" + response.body(), Toast.LENGTH_SHORT).show();
//                     Load Login Activity
                   Intent signInIntent = new Intent(VerificationActivity.this, SignInActivity.class);
                   startActivity(signInIntent);
                } else {
                    Log.e(TAG, response.toString());
                    VerificationActivity.this.txtViewTokenErrMessage.setText("Invalid Token");
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, "Failed api call", Toast.LENGTH_LONG).show();
            }
        });
    }
}
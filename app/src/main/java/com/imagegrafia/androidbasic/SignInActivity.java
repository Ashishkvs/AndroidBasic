package com.imagegrafia.androidbasic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imagegrafia.androidbasic.api.LoginApiService;
import com.imagegrafia.androidbasic.api.ApiServiceConfig;
import com.imagegrafia.androidbasic.api.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    TextView txtViewErrMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        txtViewErrMessage = findViewById(R.id.txtViewErrMessage);
        findViewById(R.id.imageView3);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> signInUser(editTextEmailAddress.getText().toString(), editTextPassword.getText().toString()));

    }

    private void signInUser(String email, String password) {
        Log.i(TAG, email + ":" + password);
        Retrofit retrofit = ApiServiceConfig.retroFitBuilder();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Call<Object> call = loginApiService.userLogin(user);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i(TAG, response.toString());
                if (response.code() == 200) {
                    Toast.makeText(SignInActivity.this, "Logged In Successfully" + response.body(), Toast.LENGTH_SHORT).show();
                    // Load Live Task Activity
                    Intent liveTaskActivityIntent = new Intent(SignInActivity.this, LiveTaskActivity.class);
                    String auth = "Basic " + Base64.getEncoder().encodeToString((email + ":" + password).getBytes(StandardCharsets.UTF_8));
                    liveTaskActivityIntent.putExtra(AppConstant.AUTHORIZATION, auth);
                    startActivity(liveTaskActivityIntent);
                } else {
                    Log.e(TAG, response.toString());
                    SignInActivity.this.txtViewErrMessage.setText("Invalid Login Details");
                    Toast.makeText(SignInActivity.this, "Logged In Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "Failed fetching record", Toast.LENGTH_LONG).show();
            }
        });
    }
}
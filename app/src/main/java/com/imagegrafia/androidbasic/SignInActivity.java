package com.imagegrafia.androidbasic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imagegrafia.androidbasic.api.LoginApiService;
import com.imagegrafia.androidbasic.api.User;
import com.imagegrafia.androidbasic.service.AppConstant;
import com.imagegrafia.androidbasic.utility.LoadingDialog;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    LoadingDialog loadingDialog;
    TextView txtViewErrMessage;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        loadingDialog = new LoadingDialog(SignInActivity.this);
        EditText editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        txtViewErrMessage = findViewById(R.id.txtViewErrMessage);
        findViewById(R.id.imageView3);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            loadingDialog.loadProgressBar();
            signInUser(editTextEmailAddress.getText().toString(), editTextPassword.getText().toString());
        });
        Button btnSignupPage = findViewById(R.id.btnSignupPage);
        btnSignupPage.setOnClickListener(v -> {
            Intent signInIntent = new Intent(SignInActivity.this, SignupActivity.class);
            startActivity(signInIntent);
            finish();
        });

        //shared pref
        sharedPreferences = getSharedPreferences(AppConstant.CURRENT_USER, Context.MODE_PRIVATE);

    }

    private void signInUser(String email, String password) {
        Log.i(TAG, email + ":" + password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
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

                    //shared pref
                    SharedPreferences.Editor spEditor = sharedPreferences.edit();
                    spEditor.putString(AppConstant.AUTHORIZATION, auth);
                    spEditor.commit();
                } else {
                    SignInActivity.this.loadingDialog.dismissProgressBar();
                    Log.e(TAG, response.toString());
                    SignInActivity.this.txtViewErrMessage.setText("Invalid Login Details");
                    Toast.makeText(SignInActivity.this, "Logged In Failed", Toast.LENGTH_LONG).show();
                }
                SignInActivity.this.loadingDialog.dismissProgressBar();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                SignInActivity.this.loadingDialog.dismissProgressBar();
                Toast.makeText(SignInActivity.this, "Failed fetching record", Toast.LENGTH_LONG).show();
            }
        });
    }
}
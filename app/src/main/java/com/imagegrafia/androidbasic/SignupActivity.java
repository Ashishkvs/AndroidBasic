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
import com.imagegrafia.androidbasic.api.User;
import com.imagegrafia.androidbasic.service.AppConstant;
import com.imagegrafia.androidbasic.service.AuthService;
import com.imagegrafia.androidbasic.utility.LoadingDialog;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();
    private LoadingDialog loadingDialog;
    TextView txtViewSignupErrMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        loadingDialog = new LoadingDialog(SignupActivity.this);

        EditText editTextSignupUsername = findViewById(R.id.editTextSignupUsername);
        EditText editTextSignupEmail = findViewById(R.id.editTextSignupEmail);
        EditText editTextSignupPassword = findViewById(R.id.editTextSignupPassword);
        txtViewSignupErrMessage = findViewById(R.id.txtViewSignupErrMessage);
        findViewById(R.id.imageViewSignUp);
        Button btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(v -> {signupUser(editTextSignupUsername.getText().toString(),
                editTextSignupEmail.getText().toString(), editTextSignupPassword.getText().toString());
            SignupActivity.this.loadingDialog.loadProgressBar();

        });

        Button btnLoginPage = findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(v ->{
            Intent signInIntent = new Intent(SignupActivity.this, SignInActivity.class);
            startActivity(signInIntent);
            finish();
        });

    }

    private void signupUser(String name, String email, String password) {
        Log.i(TAG, email + ":" + password);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        Call<Object> call = loginApiService.userSignup(user);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i(TAG, response.toString());
                if (response.code() == 201) {
                    Toast.makeText(SignupActivity.this, "SignUp In Successfully" + response.body(), Toast.LENGTH_SHORT).show();
                    // Load Verification Activity
                    new AuthService().callCustomIntent(user,SignupActivity.this, VerificationActivity.class);
                } else {
                    Log.e(TAG, response.toString());
                    SignupActivity.this.txtViewSignupErrMessage.setText("Invalid signup details");
                    Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_LONG).show();
                }
                SignupActivity.this.loadingDialog.dismissProgressBar();

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                SignupActivity.this.loadingDialog.dismissProgressBar();
                Toast.makeText(SignupActivity.this, "Failed fetching record", Toast.LENGTH_LONG).show();
            }
        });
    }
}
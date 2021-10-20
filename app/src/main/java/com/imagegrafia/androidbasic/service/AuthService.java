package com.imagegrafia.androidbasic.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.imagegrafia.androidbasic.api.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthService {
    public void callCustomIntent(User user, Context packageContext, Class<?> cls) {
        Log.i("com.imagegrafia.androidbasic.service.AuthService", "callCustomIntent :" + user);
//        Intent liveTaskActivityIntent = new Intent(SignInActivity.this, LiveTaskActivity.class);
        Intent intent = new Intent(packageContext, cls);
        String auth = "Basic " + Base64.getEncoder().encodeToString((user.getName() + ":" + user.getPassword()).getBytes(StandardCharsets.UTF_8));
        intent.putExtra(AppConstant.AUTHORIZATION, auth);
        packageContext.startActivity(intent);
    }
}

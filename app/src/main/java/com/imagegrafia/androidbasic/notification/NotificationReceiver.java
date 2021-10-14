package com.imagegrafia.androidbasic.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedMessage = intent.getStringExtra("Accept");
        Toast.makeText(context, "receivedMessage"+receivedMessage,Toast.LENGTH_SHORT).show();
    }
}

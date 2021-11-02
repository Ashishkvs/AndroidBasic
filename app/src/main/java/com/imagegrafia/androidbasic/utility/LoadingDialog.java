package com.imagegrafia.androidbasic.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.imagegrafia.androidbasic.R;

public class LoadingDialog {
    private Activity mActivity;
    private AlertDialog mAlertDialog;

    public LoadingDialog(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void loadProgressBar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater layoutInflater = mActivity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(true);

        mAlertDialog = builder.create();
        mAlertDialog.show();

    }

    public void dismissProgressBar() {
        mAlertDialog.dismiss();
    }
}

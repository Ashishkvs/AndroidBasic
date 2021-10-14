package com.imagegrafia.androidbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MaterialLookActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_look);
        MaterialButtonToggleGroup toggleButtonGroup = findViewById(R.id.toggleGroupButton);
        toggleButtonGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            Log.i(TAG,"checkId"+checkedId);
        });
    }
}
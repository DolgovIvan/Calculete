package com.dolgov.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFERENCES = "nightPreferences";
    public static final String KEY_THEME = "night";
    private SharedPreferences sharedPreferences;
    private SwitchCompat theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this::onClick);
        theme = findViewById(R.id.theme);

        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        checkNightModeActivated();

        theme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
            recreate();
        });
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_THEME, nightMode).apply();
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(KEY_THEME, false)) {
            theme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            theme.setText("Темная тема");
        } else {
            theme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            theme.setText("Светлая тема");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReturn:
                Intent intent = new Intent();
                intent.putExtra(KEY_THEME, sharedPreferences.getBoolean(KEY_THEME, false));
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
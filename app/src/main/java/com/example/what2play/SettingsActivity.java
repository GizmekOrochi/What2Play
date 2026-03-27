package com.example.what2play;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends BaseActivity {

    private String selectedLang;

    private ImageView flagEnglish;
    private ImageView flagFrench;
    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "onCreate");

        flagEnglish = findViewById(R.id.flagEnglish);
        flagFrench = findViewById(R.id.flagFrench);
        findViewById(R.id.btnBack);
        findViewById(R.id.btnApply);

        //Load saved language
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        selectedLang = prefs.getString("lang", "en");

        Log.d(TAG, "Loaded language: " + selectedLang);

        //Apply UI manually
        if (selectedLang.equals("fr")) {
            flagFrench.setAlpha(1f);
            flagEnglish.setAlpha(0.5f);
        } else {
            flagEnglish.setAlpha(1f);
            flagFrench.setAlpha(0.5f);
        }

    }
    private void updateFlagDisplay() {
        if ("fr".equals(selectedLang)) {
            flagFrench.setAlpha(1f);
            flagEnglish.setAlpha(0.5f);
        } else {
            flagEnglish.setAlpha(1f);
            flagFrench.setAlpha(0.5f);
        }
    }

    public void clickEnglish(View view) {
        selectedLang = "en";
        Log.d(TAG, "English selected");
        updateFlagDisplay();
    }

    public void clickFrench(View view) {
        selectedLang = "fr";
        Log.d(TAG, "French selected");
        updateFlagDisplay();
    }

    public void clickApply(View view) {
        Log.d(TAG, "Apply clicked with the language: " + selectedLang);

        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        prefs.edit().putString("lang", selectedLang).apply();

        Log.d(TAG, "Language has been saved");

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void clickBack(View view) {
        Log.d(TAG, "Back has been pressed");
        finish();
    }
}
package com.example.what2play;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "Settings";

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences prefs = newBase.getSharedPreferences(TAG, MODE_PRIVATE);

        String lang = prefs.getString("lang", "en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Log.d(TAG, "New local with language: " + lang);

        Configuration config = new Configuration();
        config.setLocale(locale);

        Context context = newBase.createConfigurationContext(config);
        super.attachBaseContext(context);
    }
}
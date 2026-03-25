package com.example.what2play;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

//This class is used a mother class for activities to apply a new local each time an activity is created
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences prefs = newBase.getSharedPreferences("Settings", MODE_PRIVATE);

        //Get selected language and create new local
        String lang = prefs.getString("lang", "en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Log.d("BaseActivity", "New local with language: " + lang);

        Configuration config = new Configuration();
        config.setLocale(locale);

        Context context = newBase.createConfigurationContext(config);
        super.attachBaseContext(context);
    }
}
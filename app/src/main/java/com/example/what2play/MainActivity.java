package com.example.what2play;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.DatabaseInitializer;
import com.example.what2play.database.entities.Artist;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);

        //Create database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "what2play-db").allowMainThreadQueries().build();
        //Insert test data
        DatabaseInitializer.populate(db);
        //Get data from DB
        List<Artist> artists = db.artistDao().getAll();
        //Display data
        StringBuilder result = new StringBuilder();
        for (Artist a : artists) {
            result.append(a.id).append(" - ").append(a.name).append("\n");
        }

        text.setText(result.toString());
    }
}
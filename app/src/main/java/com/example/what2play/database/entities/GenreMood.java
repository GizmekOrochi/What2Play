package com.example.what2play.database.entities;

import androidx.room.Entity;

@Entity(
        tableName = "genre_mood",
        primaryKeys = {"genreId", "moodId"}
)
public class GenreMood {

    public int genreId;
    public int moodId;
}
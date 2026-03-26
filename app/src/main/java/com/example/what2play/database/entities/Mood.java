package com.example.what2play.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "moods")
public class Mood {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;
}
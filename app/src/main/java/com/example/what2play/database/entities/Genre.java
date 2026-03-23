package com.example.what2play.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "genres")
public class Genre {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

}
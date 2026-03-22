package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.what2play.database.entities.Mood;

import java.util.List;

@Dao
public interface MoodDao {

    @Insert
    long insert(Mood mood);

    @Query("SELECT * FROM moods")
    List<Mood> getAll();
}
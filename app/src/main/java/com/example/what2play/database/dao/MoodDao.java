package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Mood;

import java.util.List;

@Dao
public interface MoodDao {
    @Query("SELECT COUNT(*) FROM moods")
    int count();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Mood mood);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Mood... moods);

    @Query("SELECT * FROM moods")
    List<Mood> getAll();

    @Query("DELETE FROM moods")
    void clear();
}
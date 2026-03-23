package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Genre genre);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Genre... genres);

    @Query("SELECT * FROM genres")
    List<Genre> getAll();

    @Query("DELETE FROM genres")
    void clear();
}
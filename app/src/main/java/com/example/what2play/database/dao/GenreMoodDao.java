package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.what2play.database.entities.GenreMood;

import java.util.List;

@Dao
public interface GenreMoodDao {

    @Insert
    long insert(GenreMood relation);

    @Query("SELECT * FROM genre_mood WHERE genreId = :genreId")
    List<GenreMood> getMoodsForGenre(int genreId);
}
package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.GenreMood;
import com.example.what2play.database.entities.Mood;

import java.util.List;

@Dao
public interface GenreMoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(GenreMood relation);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(GenreMood... relations);

    //Get moods of a genre
    @Query("SELECT * FROM moods INNER JOIN genre_mood ON moods.id = genre_mood.moodId WHERE genre_mood.genreId = :genreId")
    List<Mood> getMoodsForGenre(int genreId);

    @Query("DELETE FROM genre_mood")
    void clear();
}
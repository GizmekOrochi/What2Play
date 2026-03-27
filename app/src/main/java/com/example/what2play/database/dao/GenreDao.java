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

    @Query("SELECT * FROM genres WHERE id = :genreId LIMIT 1")
    Genre getById(int genreId);

    @Query("SELECT * FROM genres WHERE name = :genreName LIMIT 1")
    Genre getByName(String genreName);

    @Query("SELECT * FROM genres WHERE name IN ('Boom Bap', 'West Coast', 'Trap', 'Cloud Rap') ORDER BY CASE name WHEN 'Boom Bap' THEN 1 WHEN 'West Coast' THEN 2 WHEN 'Trap' THEN 3 WHEN 'Cloud Rap' THEN 4 END")
    List<Genre> getRapGenresForEmojiQuestion();

    @Query("SELECT COUNT(*) FROM genres")
    int count();

    @Query("DELETE FROM genres")
    void clear();
}
package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Artist;

import java.util.List;

@Dao
public interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Artist artist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Artist... artists);

    @Query("SELECT * FROM artists")
    List<Artist> getAll();

    @Query("SELECT * FROM artists WHERE id = :id")
    Artist getById(int id);

    @Query("SELECT * FROM artists WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    Artist findByName(String name);

    @Query("DELETE FROM artists")
    void clear();
}
package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.what2play.database.entities.Artist;

import java.util.List;

@Dao
public interface ArtistDao {

    @Insert
    long insert(Artist artist);

    @Query("SELECT * FROM artists")
    List<Artist> getAll();

    @Query("SELECT * FROM artists WHERE id = :id")
    Artist getById(int id);
}
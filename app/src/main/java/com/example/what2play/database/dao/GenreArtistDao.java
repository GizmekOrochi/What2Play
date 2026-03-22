package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.what2play.database.entities.GenreArtist;

import java.util.List;

@Dao
public interface GenreArtistDao {

    @Insert
    long insert(GenreArtist relation);

    @Query("SELECT * FROM genre_artist WHERE artistId = :artistId")
    List<GenreArtist> getGenresForArtist(int artistId);
}
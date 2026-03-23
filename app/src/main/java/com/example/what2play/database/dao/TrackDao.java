package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Track;

import java.util.List;

@Dao
public interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Track track);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Track... tracks);

    @Query("SELECT * FROM tracks")
    List<Track> getAll();

    @Query("SELECT * FROM tracks WHERE id = :id")
    Track getById(int id);

    @Query("DELETE FROM tracks")
    void clear();
}
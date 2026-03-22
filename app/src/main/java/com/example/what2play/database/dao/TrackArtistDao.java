package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.what2play.database.entities.TrackArtist;

import java.util.List;

@Dao
public interface TrackArtistDao {

    @Insert
    long insert(TrackArtist relation);

    @Query("SELECT * FROM track_artist WHERE trackId = :trackId")
    List<TrackArtist> getArtistsForTrack(int trackId);
}
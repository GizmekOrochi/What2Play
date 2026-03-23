package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Track;
import com.example.what2play.database.entities.TrackArtist;

import java.util.List;

@Dao
public interface TrackArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(TrackArtist relation);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(TrackArtist... relations);

    //Get all artists for a track
    @Query("SELECT * FROM artists INNER JOIN track_artist ON artists.id = track_artist.artistId WHERE track_artist.trackId = :trackId")
    List<Artist> getArtistsForTrack(int trackId);

    //Get all tracks for an artist
    @Query("SELECT * FROM tracks INNER JOIN track_artist ON tracks.id = track_artist.trackId WHERE track_artist.artistId = :artistId")
    List<Track> getTracksForArtist(int artistId);

    @Query("DELETE FROM track_artist")
    void clear();
}
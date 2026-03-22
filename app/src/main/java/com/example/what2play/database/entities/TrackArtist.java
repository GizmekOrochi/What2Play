package com.example.what2play.database.entities;

import androidx.room.Entity;

@Entity(
        tableName = "track_artist",
        primaryKeys = {"trackId", "artistId"}
)
public class TrackArtist {

    public int trackId;
    public int artistId;
}
package com.example.what2play.database.entities;

import androidx.room.Entity;

@Entity(
        tableName = "genre_artist",
        primaryKeys = {"genreId", "artistId"}
)
public class GenreArtist {

    public int genreId;
    public int artistId;
}
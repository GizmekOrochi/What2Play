package com.example.what2play.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.what2play.database.dao.ArtistDao;
import com.example.what2play.database.dao.GenreDao;
import com.example.what2play.database.dao.GenreArtistDao;
import com.example.what2play.database.dao.GenreMoodDao;
import com.example.what2play.database.dao.MoodDao;
import com.example.what2play.database.dao.TrackDao;
import com.example.what2play.database.dao.TrackArtistDao;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.GenreArtist;
import com.example.what2play.database.entities.GenreMood;
import com.example.what2play.database.entities.Mood;
import com.example.what2play.database.entities.Track;
import com.example.what2play.database.entities.TrackArtist;

@Database(
        entities = {Artist.class,
                Genre.class,
                GenreArtist.class,
                GenreMood.class,
                Mood.class,
                Track.class,
                TrackArtist.class
        },
        version = 2
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArtistDao artistDao();
    public abstract GenreDao genreDao();
    public abstract GenreArtistDao genreArtistDao();
    public abstract GenreMoodDao genreMoodDao();
    public abstract MoodDao moodDao();
    public abstract TrackDao trackDao();
    public abstract TrackArtistDao trackArtistDao();
}
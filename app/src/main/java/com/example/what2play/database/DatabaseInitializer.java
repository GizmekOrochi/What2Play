package com.example.what2play.database;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.Track;
import com.example.what2play.database.entities.TrackArtist;
import com.example.what2play.database.entities.GenreArtist;

public class DatabaseInitializer {

    public static void populate(AppDatabase db) {

        //Clear the data in the table
        db.clearAllTables();
        //Reset all the ID in the table REMOVE THIS SHIT BEFORE RETURNING THE PROJECT
        db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM sqlite_sequence");

        //ARTISTS
        Artist daftPunk = new Artist();
        daftPunk.name = "Daft Punk";
        long daftPunkId = db.artistDao().insert(daftPunk);

        Artist kavinsky = new Artist();
        kavinsky.name = "Kavinsky";
        long kavinskyId = db.artistDao().insert(kavinsky);

        //GENRES
        Genre electro = new Genre();
        electro.name = "Electro";
        long electroId = db.genreDao().insert(electro);

        Genre synthwave = new Genre();
        synthwave.name = "Synthwave";
        long synthwaveId = db.genreDao().insert(synthwave);

        //TRACKS
        Track t1 = new Track();
        t1.name = "One More Time";
        t1.language = "EN";
        long track1Id = db.trackDao().insert(t1);

        Track t2 = new Track();
        t2.name = "Nightcall";
        t2.language = "EN";
        long track2Id = db.trackDao().insert(t2);

        //RELATION Track <> Artist
        TrackArtist ta1 = new TrackArtist();
        ta1.trackId = (int) track1Id;
        ta1.artistId = (int) daftPunkId;
        db.trackArtistDao().insert(ta1);

        TrackArtist ta2 = new TrackArtist();
        ta2.trackId = (int) track2Id;
        ta2.artistId = (int) kavinskyId;
        db.trackArtistDao().insert(ta2);

        //RELATION Genre <> Artist
        GenreArtist ga1 = new GenreArtist();
        ga1.genreId = (int) electroId;
        ga1.artistId = (int) daftPunkId;
        db.genreArtistDao().insert(ga1);

        GenreArtist ga2 = new GenreArtist();
        ga2.genreId = (int) synthwaveId;
        ga2.artistId = (int) kavinskyId;
        db.genreArtistDao().insert(ga2);
    }
}
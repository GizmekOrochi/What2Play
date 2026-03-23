package com.example.what2play.database;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.Mood;
import com.example.what2play.database.entities.Track;
import com.example.what2play.database.entities.TrackArtist;
import com.example.what2play.database.entities.GenreArtist;

import java.util.HashMap;
import java.util.Map;

public class DatabaseInitializer {

    // simple struct to hold track info
    static class TrackData {
        String trackName;
        String artistName;
        String genreName;
        String moodName;
        String language;

        TrackData(String track, String artist, String genre, String mood, String lang) {
            this.trackName = track;
            this.artistName = artist;
            this.genreName = genre;
            this.moodName = mood;
            this.language = lang;
        }
    }

    // main method to fill db
    public static void populate(AppDatabase db) {

        // reset db (only for testing)
        db.clearAllTables();
        db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM sqlite_sequence");

        // sample data
        TrackData[] data = {
                new TrackData("One More Time", "Daft Punk", "Electro", "Happy", "EN"),
                new TrackData("Nightcall", "Kavinsky", "Synthwave", "Dark", "EN")
        };

        // build base tables
        Map<String, Integer> artistMap = addArtists(db, data);
        Map<String, Integer> genreMap = addGenres(db, data);
        Map<String, Integer> moodMap = addMoods(db, data);

        // add tracks and the relations
        addTracksAndRelations(db, data, artistMap, genreMap, moodMap);
    }

    // insert artists
    private static Map<String, Integer> addArtists(AppDatabase db, TrackData[] data) {

        Map<String, Integer> map = new HashMap<>();

        for (TrackData d : data) {
            if (!map.containsKey(d.artistName)) { // avoid duplication
                Artist a = new Artist();
                a.name = d.artistName;
                int id = (int) db.artistDao().insert(a);
                map.put(d.artistName, id);
            }
        }

        return map;
    }

    // insert genres
    private static Map<String, Integer> addGenres(AppDatabase db, TrackData[] data) {

        Map<String, Integer> map = new HashMap<>();

        for (TrackData d : data) {
            if (!map.containsKey(d.genreName)) { // avoid duplication
                Genre g = new Genre();
                g.name = d.genreName;
                int id = (int) db.genreDao().insert(g);
                map.put(d.genreName, id);
            }
        }

        return map;
    }

    // insert moods
    private static Map<String, Integer> addMoods(AppDatabase db, TrackData[] data) {

        Map<String, Integer> map = new HashMap<>();

        for (TrackData d : data) {
            if (!map.containsKey(d.moodName)) { // avoid duplication
                Mood m = new Mood();
                m.name = d.moodName;
                int id = (int) db.moodDao().insert(m);
                map.put(d.moodName, id);
            }
        }

        return map;
    }

    // insert tracks and link tables
    private static void addTracksAndRelations(
            AppDatabase db,
            TrackData[] data,
            Map<String, Integer> artistMap,
            Map<String, Integer> genreMap,
            Map<String, Integer> moodMap
    ) {

        for (TrackData d : data) {

            // create track
            Track t = new Track();
            t.name = d.trackName;
            t.language = d.language;

            int trackId = (int) db.trackDao().insert(t);

            // get ids from maps
            int artistId = artistMap.get(d.artistName);
            int genreId = genreMap.get(d.genreName);
            int moodId = moodMap.get(d.moodName);

            // link track <-> artist
            TrackArtist ta = new TrackArtist();
            ta.trackId = trackId;
            ta.artistId = artistId;
            db.trackArtistDao().insert(ta);

            // link genre <-> artist
            GenreArtist ga = new GenreArtist();
            ga.genreId = genreId;
            ga.artistId = artistId;
            db.genreArtistDao().insert(ga);
        }
    }
}
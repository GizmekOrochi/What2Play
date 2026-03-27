package com.example.what2play.database;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.GenreArtist;
import com.example.what2play.database.entities.GenreMood;
import com.example.what2play.database.entities.Mood;
import com.example.what2play.database.entities.Track;
import com.example.what2play.database.entities.TrackArtist;

import java.util.HashMap;
import java.util.Map;

public class DatabaseInitializer {

    public static void initiate(AppDatabase db) {
        db.runInTransaction(() -> {
            boolean dbComplete =
                    db.genreDao().count() == MusicData.GENRES.length &&
                            db.moodDao().count() == MusicData.MOODS.length &&
                            db.artistDao().getAll().size() == MusicData.ARTISTS.length &&
                            db.trackDao().getAll().size() == MusicData.TRACKS.length;

            if (dbComplete) {
                return;
            }

            db.trackArtistDao().clear();
            db.trackDao().clear();
            db.genreArtistDao().clear();
            db.genreMoodDao().clear();
            db.artistDao().clear();
            db.genreDao().clear();
            db.moodDao().clear();

            Map<String, Integer> moodMap = addMoods(db);
            Map<String, Integer> genreMap = addGenres(db);
            Map<String, Integer> artistMap = addArtists(db);

            linkGenreMood(db, genreMap, moodMap);
            linkArtistGenre(db, artistMap, genreMap);
            addTracks(db, artistMap);
        });
    }

    private static Map<String, Integer> addMoods(AppDatabase db) {
        Map<String, Integer> map = new HashMap<>();
        for (MusicData.MoodData m : MusicData.MOODS) {
            Mood mood = new Mood();
            mood.name = m.name;
            int id = (int) db.moodDao().insert(mood);
            map.put(m.name, id);
        }
        return map;
    }

    private static Map<String, Integer> addGenres(AppDatabase db) {
        Map<String, Integer> map = new HashMap<>();
        for (MusicData.GenreData g : MusicData.GENRES) {
            Genre genre = new Genre();
            genre.name = g.name;
            int id = (int) db.genreDao().insert(genre);
            map.put(g.name, id);
        }
        return map;
    }

    private static Map<String, Integer> addArtists(AppDatabase db) {
        Map<String, Integer> map = new HashMap<>();
        for (MusicData.ArtistData a : MusicData.ARTISTS) {
            Artist artist = new Artist();
            artist.name = a.name;
            int id = (int) db.artistDao().insert(artist);
            map.put(a.name, id);
        }
        return map;
    }

    private static void linkGenreMood(AppDatabase db, Map<String, Integer> genreMap, Map<String, Integer> moodMap) {
        for (MusicData.GenreMoodData gm : MusicData.GENRE_MOOD) {
            GenreMood rel = new GenreMood();
            rel.genreId = genreMap.get(gm.genre);
            rel.moodId = moodMap.get(gm.mood);
            db.genreMoodDao().insert(rel);
        }
    }

    private static void linkArtistGenre(AppDatabase db, Map<String, Integer> artistMap, Map<String, Integer> genreMap) {
        for (MusicData.ArtistGenreData ag : MusicData.ARTIST_GENRE) {
            GenreArtist rel = new GenreArtist();
            rel.artistId = artistMap.get(ag.artist);
            rel.genreId = genreMap.get(ag.genre);
            db.genreArtistDao().insert(rel);
        }
    }

    private static void addTracks(AppDatabase db, Map<String, Integer> artistMap) {
        for (MusicData.TrackData tData : MusicData.TRACKS) {
            Track t = new Track();
            t.name = tData.name;
            t.link = tData.link;

            int trackId = (int) db.trackDao().insert(t);

            int artistId = artistMap.get(tData.artist);

            TrackArtist ta = new TrackArtist();
            ta.trackId = trackId;
            ta.artistId = artistId;
            db.trackArtistDao().insert(ta);
        }
    }
}
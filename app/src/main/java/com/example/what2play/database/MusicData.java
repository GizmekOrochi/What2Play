package com.example.what2play.database;

public class MusicData {

    //Mood structs
    public static class MoodData {
        public String name;

        public MoodData(String name) {
            this.name = name;
        }
    }

    //Genre struct
    public static class GenreData {
        public String name;

        public GenreData(String name) {
            this.name = name;
        }
    }

    //Artist struct
    public static class ArtistData {
        public String name;

        public ArtistData(String name) {
            this.name = name;
        }
    }

    //Track truck
    public static class TrackData {
        public String name;
        public String artist;
        public String genre;
        public String language;
        public String link;

        public TrackData(String name, String artist, String genre, String language, String link) {
            this.name = name;
            this.artist = artist;
            this.genre = genre;
            this.language = language;
            this.link = link;
        }
    }

    //Relation structs
    public static class GenreMoodData {
        public String genre;
        public String mood;

        public GenreMoodData(String genre, String mood) {
            this.genre = genre;
            this.mood = mood;
        }
    }

    public static class ArtistGenreData {
        public String artist;
        public String genre;

        public ArtistGenreData(String artist, String genre) {
            this.artist = artist;
            this.genre = genre;
        }
    }

    //Default data
    public static MoodData[] MOODS = {
            new MoodData("Happy"),
            new MoodData("Dark")
    };

    public static GenreData[] GENRES = {
            new GenreData("Electro"),
            new GenreData("Synthwave"),
    };

    public static ArtistData[] ARTISTS = {
            new ArtistData("Daft Punk"),
            new ArtistData("Kavinsky")
    };

    public static TrackData[] TRACKS = {
            new TrackData("One More Time", "Daft Punk", "Electro", "EN", "ed"),
            new TrackData("Nightcall", "Kavinsky", "Synthwave", "EN", "ds")
    };

    public static GenreMoodData[] GENRE_MOOD = {
            new GenreMoodData("Electro", "Happy"),
            new GenreMoodData("Synthwave", "Dark")
    };

    public static ArtistGenreData[] ARTIST_GENRE = {
            new ArtistGenreData("Daft Punk", "Electro"),
            new ArtistGenreData("Kavinsky", "Synthwave")
    };
}
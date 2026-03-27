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
            new MoodData("Dark"),
            new MoodData("Energetic"),
            new MoodData("Epic"),
            new MoodData("Melancholic"),
            new MoodData("Aggressive"),
            new MoodData("Chill"),
            new MoodData("Dreamy")
    };

    public static GenreData[] GENRES = {
            new GenreData("Electro"),
            new GenreData("Synthwave"),
            new GenreData("Metal"),
            new GenreData("Rock")
    };

    public static ArtistData[] ARTISTS = {
            new ArtistData("Deep Beyond"),
            new ArtistData("Daft Punk"),
            new ArtistData("Kavinsky"),
            new ArtistData("Timecop1983"),
            new ArtistData("The Midnight"),
            new ArtistData("Neon Nox"),
            new ArtistData("Scandroid"),

            new ArtistData("Skillet"),
            new ArtistData("Brothers of Metal"),
            new ArtistData("Feuerschwanz"),
            new ArtistData("Powerwolf"),
            new ArtistData("Sabaton"),
            new ArtistData("Beast In Black"),

            new ArtistData("Linkin Park"),
            new ArtistData("The Offspring"),
            new ArtistData("Imagine Dragons"),
            new ArtistData("Fall Out Boy"),
            new ArtistData("Within Temptation"),
            new ArtistData("Starset")
    };

    public static TrackData[] TRACKS = {
            new TrackData("TEARS", "Deep Beyond", "Synthwave", "FR", "1VF5002okqUFfYScHuJuIY"),
            new TrackData("鋼鉄の街 - Red Reboot", "Deep Beyond", "Synthwave", "FR", "2G3ILNCK0Mx3bewzMIr6ci"),
            new TrackData("Holding The Horizon", "Deep Beyond", "Synthwave", "FR", "2JfAEKdzCxl0mk3NVJH7Uv"),
            new TrackData("After The Last Light", "Timecop1983", "Synthwave", "EN", "0peTa9Eruzem0whLqHSCAs"),
            new TrackData("Nightfall", "Timecop1983", "Synthwave", "EN", "1HTnyvQzCoFbOoKIEcv3Lx"),
            new TrackData("Until the End", "Timecop1983", "Synthwave", "EN", "5OcPhJ2cXJonwgIkLAdDBH"),
            new TrackData("Nightcall", "Kavinsky", "Synthwave", "EN", "0U0ldCRmgCqhVvD6ksG63j"),
            new TrackData("Odd Look", "Kavinsky", "Synthwave", "EN", "3Q6mwJseOFYBJ10d5CXp4o"),
            new TrackData("Pulsar", "Kavinsky", "Synthwave", "EN", "4GRMPDD3V6rM2BlmRdYCUJ"),
            new TrackData("Sanctuary", "The Midnight", "Synthwave", "EN", "7H9ziHofMuHshK1F9tuF4w"),
            new TrackData("River of Darkness", "The Midnight", "Synthwave", "EN", "42ygcFFXLx8VBqsAlClqsi"),
            new TrackData("Sunset", "The Midnight", "Synthwave", "EN", "6mB9A9YLbY4jxpKX5EYAnT"),
            new TrackData("Fahrenheit", "Neon Nox", "Synthwave", "EN", "29J0OTJNBsJlbLGjVagx6I"),
            new TrackData("Dreamcatcher", "Neon Nox", "Synthwave", "EN", "4VB23Lg94aTn0px1HRRnwx"),
            new TrackData("Long Way Home", "Neon Nox", "Synthwave", "EN", "3A6N4HinZUARfDEgzj3Kmr"),
            new TrackData("Neo Tokyo", "Scandroid", "Synthwave", "EN", "0i9IlmXcLt7B6N0fixuIK2"),
            new TrackData("Nighttime", "Scandroid", "Synthwave", "EN", "3P8xspcup7dGfjg2VixRAl"),
            new TrackData("The Resistance", "Skillet", "Metal", "EN", "5O9Dz0h08LuBi0aVvDcylh"),
            new TrackData("Monster", "Skillet", "Metal", "EN", "2UREu1Y8CO4jXkbvqAtP7g"),
            new TrackData("Standing In The Storm", "Skillet", "Metal", "EN", "72DI6pnhy26WMx7fI1fL07"),
            new TrackData("Son of Odin", "Brothers of Metal", "Metal", "EN", "1d17u8gNyjAwhp9N9C7Io5"),
            new TrackData("Rise of the Valkyries", "Brothers of Metal", "Metal", "EN", "1npDycvcBm6bTnaajiTv3X"),
            new TrackData("The Other Son of Odin", "Brothers of Metal", "Metal", "EN", "480bA7oNCvzQpDf07z3kny"),
            new TrackData("Blinding Lights", "Feuerschwanz", "Metal", "DE", "480bA7oNCvzQpDf07z3kny"),
            new TrackData("Krampus", "Feuerschwanz", "Metal", "DE", "5WpYAPEWJo4hByY3rNsDpc"),
            new TrackData("Warriors of the World", "Feuerschwanz", "Metal", "EN", "5bAzurNn8l106dgmyvk46s"),
            new TrackData("Demons Are a Girl’s Best Friend", "Powerwolf", "Metal", "EN", "1KCDsPKFISNM2YZFFAoI7s"),
            new TrackData("Sanctified With Dynamite", "Powerwolf", "Metal", "EN", "2ZUUturolW3VpzXWzPEy2X"),
            new TrackData("In the Name of God", "Powerwolf", "Metal", "EN", "27krtJL99aNMCkqPdkSqii"),
            new TrackData("The Last Stand", "Sabaton", "Metal", "EN", "0bUgTRe5st6TMbRCEjKezX"),
            new TrackData("The Last Battle", "Sabaton", "Metal", "EN", "7hrW22GLqFLwQOfQYLEw6q"),
            new TrackData("Shiroyama", "Sabaton", "Metal", "EN", "3o2dn2O0FCVsWDFSh8qxgG"),
            new TrackData("Sweet True Lies", "Beast In Black", "Metal", "EN", "2cyJkqB2OYHwWWvKGtrJ0r"),
            new TrackData("Highway To Mars", "Beast In Black", "Metal", "EN", "2uoiILxQMf5BRSTivExIgV"),
            new TrackData("Eternal Fire", "Beast In Black", "Metal", "EN", "0QUJLDnDTiKQ1XQs6tRffK"),
            new TrackData("Numb", "Linkin Park", "Rock", "EN", "2nLtzopw4rPReszdYBJU6h"),
            new TrackData("Friendly Fire", "Linkin Park", "Rock", "EN", "1rAzOr3zpUDRtN2zsqGHiG"),
            new TrackData("One More Light", "Linkin Park", "Rock", "EN", "3xXBsjrbG1xQIm1xv1cKOt"),
            new TrackData("You're Gonna Go Far, Kid", "The Offspring", "Rock", "EN", "6TfBA04WJ3X1d1wXhaCFVT"),
            new TrackData("Why Don't You Get A Job", "The Offspring", "Rock", "EN", "0sNKiz82ATCvT3f3XVVUUj"),
            new TrackData("Believer", "Imagine Dragons", "Rock", "EN", "0pqnGHJpmpxLKifKRmU6WP"),
            new TrackData("Enemy", "Imagine Dragons", "Rock", "EN", "1r9xUipOqoNwggBpENDsvJ"),
            new TrackData("Centuries", "Fall Out Boy", "Rock", "EN", "04aAxqtGp5pv12UXAg4pkq"),
            new TrackData("Immortals", "Fall Out Boy", "Rock", "EN", "3Te8uLyit6X3ncNW8Fp3K2"),
            new TrackData("Phoenix", "Fall Out Boy", "Rock", "EN", "7jwDuO7UZvWs77KNj9HbvF"),
            new TrackData("Paradise", "Within Temptation", "Rock", "EN", "1PkOm35a0G1r33rVrrK6b0"),
            new TrackData("The Unforgiving", "Within Temptation", "Rock", "EN", "1EvpTfBqlDzPwDYzWTgTw3"),
            new TrackData("Faster", "Within Temptation", "Rock", "EN", "4XBtYPGMAYJkuJu2w4pmYl"),
            new TrackData("My Demons", "Starset", "Rock", "EN", "3Xfg7AegXaDLoD5GOUMf2e"),
            new TrackData("It Has Begun", "Starset", "Rock", "EN", "36ECEkodXYhLPyBHjZCEHh"),
            new TrackData("Monster", "Starset", "Rock", "EN", "0criiQKIY1hyU0lRbVhZ8L")
    };

    public static GenreMoodData[] GENRE_MOOD = {
            new GenreMoodData("Electro", "Happy"),
            new GenreMoodData("Electro", "Energetic"),
            new GenreMoodData("Synthwave", "Dark"),
            new GenreMoodData("Synthwave", "Dreamy"),
            new GenreMoodData("Synthwave", "Chill"),
            new GenreMoodData("Synthwave", "Melancholic"),
            new GenreMoodData("Metal", "Aggressive"),
            new GenreMoodData("Metal", "Epic"),
            new GenreMoodData("Metal", "Energetic"),
            new GenreMoodData("Rock", "Energetic"),
            new GenreMoodData("Rock", "Melancholic"),
            new GenreMoodData("Rock", "Epic")
    };

    public static ArtistGenreData[] ARTIST_GENRE = {
            new ArtistGenreData("Deep Beyond", "Synthwave"),
            new ArtistGenreData("Daft Punk", "Electro"),
            new ArtistGenreData("Kavinsky", "Synthwave"),
            new ArtistGenreData("Timecop1983", "Synthwave"),
            new ArtistGenreData("The Midnight", "Synthwave"),
            new ArtistGenreData("Neon Nox", "Synthwave"),
            new ArtistGenreData("Scandroid", "Synthwave"),
            new ArtistGenreData("Skillet", "Metal"),
            new ArtistGenreData("Brothers of Metal", "Metal"),
            new ArtistGenreData("Feuerschwanz", "Metal"),
            new ArtistGenreData("Powerwolf", "Metal"),
            new ArtistGenreData("Sabaton", "Metal"),
            new ArtistGenreData("Beast In Black", "Metal"),
            new ArtistGenreData("Linkin Park", "Rock"),
            new ArtistGenreData("The Offspring", "Rock"),
            new ArtistGenreData("Imagine Dragons", "Rock"),
            new ArtistGenreData("Fall Out Boy", "Rock"),
            new ArtistGenreData("Within Temptation", "Rock"),
            new ArtistGenreData("Starset", "Rock")
    };
}
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
            new GenreData("French house"),
            new GenreData("EDM"),
            new GenreData("Lo-Fi"),
            new GenreData("Metal"),
            new GenreData("Rock"),
            new GenreData("Jazz"),
            new GenreData("Blues"),
            new GenreData("Boom Bap"),
            new GenreData("West Coast"),
            new GenreData("Trap"),
            new GenreData("Cloud Rap")
    };

    public static ArtistData[] ARTISTS = {
            new ArtistData("Deep Beyond"),
            new ArtistData("Daft Punk"),
            new ArtistData("Justice"),
            new ArtistData("Alan Braxe"),
            new ArtistData("Modjo"),
            new ArtistData("Cassius"),
            new ArtistData("Maxtron"),
            new ArtistData("David Guetta"),
            new ArtistData("DJ Snake"),
            new ArtistData("Calvin Harris"),
            new ArtistData("Alan Walker"),
            new ArtistData("Avicii"),
            new ArtistData("Martin Garrix"),
            new ArtistData("Kavinsky"),
            new ArtistData("Timecop1983"),
            new ArtistData("The Midnight"),
            new ArtistData("Neon Nox"),
            new ArtistData("Scandroid"),
            new ArtistData("Nujabes"),
            new ArtistData("J Dilla"),
            new ArtistData("Jinsang"),
            new ArtistData("idealism"),
            new ArtistData("Tomppabeats"),
            new ArtistData("eevee"),
            new ArtistData("Duke Ellington"),
            new ArtistData("Charlie Parker"),
            new ArtistData("Miles Davis"),
            new ArtistData("Art Blakey"),
            new ArtistData("Ornette Coleman"),
            new ArtistData("Herbie Hancock"),
            new ArtistData("Robert Johnson"),
            new ArtistData("Muddy Waters"),
            new ArtistData("Stevie Ray Vaughan"),
            new ArtistData("Eric Clapton"),
            new ArtistData("B.B. King"),
            new ArtistData("Etta James"),
            new ArtistData("Migos"),
            new ArtistData("Niska"),
            new ArtistData("Future"),
            new ArtistData("Vald"),
            new ArtistData("Jeezy"),
            new ArtistData("Kaaris"),
            new ArtistData("IAM"),
            new ArtistData("Wu-Tang Clan"),
            new ArtistData("Nekfeu"),
            new ArtistData("Oxmo Puccino"),
            new ArtistData("Mobb Deep"),
            new ArtistData("The Notorious B.I.G."),
            new ArtistData("Dr. Dre"),
            new ArtistData("Snoop Dogg"),
            new ArtistData("Ice Cube"),
            new ArtistData("2Pac"),
            new ArtistData("Kendrick Lamar"),
            new ArtistData("PNL"),
            new ArtistData("Werenoi"),
            new ArtistData("A$AP Rocky"),
            new ArtistData("Travis Scott"),
            new ArtistData("Josman"),
            new ArtistData("Yung Lean"),
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
            new TrackData("Monster", "Starset", "Rock", "EN", "0criiQKIY1hyU0lRbVhZ8L"),
            new TrackData("One More Time", "Daft Punk", "French house", "EN", "0DiWol3AO6WpXZgp0goxAV"),
            new TrackData("Da Funk", "Daft Punk", "French house", "EN", "0MyY4WcN7DIfbSmp5yej5z"),
            new TrackData("Voyager", "Daft Punk", "French house", "EN", "7cMFjxhbXBpOlais7KMF3j"),
            new TrackData("D.A.N.C.E.", "Justice", "French house", "EN", "33yAEqzKXexYM3WlOYtTfQ"),
            new TrackData("Phantom Pt2", "Justice", "French house", "EN", "2A3emhzbZo7eSvEUtAm3ZS"),
            new TrackData("Rubicon", "Alan Braxe", "French house", "EN", "2xmTl0OYHCYcnkhV9RDlKq"),
            new TrackData("Intro", "Alan Braxe", "French house", "EN", "60hb5H9yL4P4SPz7lrTvUw"),
            new TrackData("Lady (Hear Me Tonight)", "Modjo", "French house", "EN", "49X0LAl6faAusYq02PRAY6"),
            new TrackData("Chillin'", "Modjo", "French house", "EN", "3Nxw6eeFpBwUbxVuSY6NC0"),
            new TrackData("Cassius 1999", "Cassius", "French house", "EN", "2R53yign0dvy3i4TLAr2Ud"),
            new TrackData("Feeling for You", "Cassius", "French house", "EN", "0UHhtA7tNKZaEPh7hYZVGP"),
            new TrackData("Titanium", "David Guetta", "EDM", "EN", "4drgH8MGED51vOGSJZKUZy"),
            new TrackData("Memories", "David Guetta", "EDM", "EN", "7fLzbEOBOae9lUnOwr7Tse"),
            new TrackData("Turn Down for What", "DJ Snake", "EDM", "EN", "67awxiNHNyjMXhVgsHuIrs"),
            new TrackData("Lean On", "DJ Snake", "EDM", "EN", "4UKJUbcuStnnxLeJmVJsSc"),
            new TrackData("Summer", "Calvin Harris", "EDM", "EN", "6YUTL4dYpB9xZO5qExPf05"),
            new TrackData("Feel So Close", "Calvin Harris", "EDM", "EN", "1gihuPhrLraKYrJMAEONyc"),
            new TrackData("Faded", "Alan Walker", "EDM", "EN", "7gHs73wELdeycvS48JfIos"),
            new TrackData("Alone", "Alan Walker", "EDM", "EN", "3LlmKSHR3Rs0Y3KHQLAYDk"),
            new TrackData("Wake Me Up", "Avicii", "EDM", "EN", "0nrRP2bk19rLc0orkWPQk2"),
            new TrackData("Levels", "Avicii", "EDM", "EN", "6Xe9wT5xeZETPwtaP2ynUz"),
            new TrackData("Animals", "Martin Garrix", "EDM", "EN", "0A9mHc7oYUoCECqByV8cQR"),
            new TrackData("Feather", "Nujabes", "Lo-Fi", "EN", "5f4GxONfMbpqRBVVt9ekh9"),
            new TrackData("Luv(sic) Part 3", "Nujabes", "Lo-Fi", "EN", "4xlpJ99yL9xYQtzG6c3hwk"),
            new TrackData("Workinonit", "J Dilla", "Lo-Fi", "EN", "33T6ABvdB3P2iYOWJnBjsQ"),
            new TrackData("Waves", "J Dilla", "Lo-Fi", "EN", "61WmlU4oDK5eBUhiMYZT1E"),
            new TrackData("affection", "Jinsang", "Lo-Fi", "EN", "0HSwIZSDeOQX8Cu9pCGkjS"),
            new TrackData("snowfall", "idealism", "Lo-Fi", "EN", "5tn0g0j6ksRufhK0RI33r8"),
            new TrackData("Monday Loop", "Tomppabeats", "Lo-Fi", "EN", "6PpTjHkwJj9LxMsqj05xGl"),
            new TrackData("violet", "eevee", "Lo-Fi", "EN", "47bnKpeG7c2Ot3H77C3z5u"),
            new TrackData("Take the A Train", "Duke Ellington", "Jazz", "EN", "2vMWQtYmg1B5JhD5Dihpke"),
            new TrackData("So What", "Miles Davis", "Jazz", "EN", "6J18r0R7rQFuZ4hiRW4P4W"),
            new TrackData("Moanin'", "Art Blakey", "Jazz", "EN", "2rS7da27yUaXKJ66s5IuJ8"),
            new TrackData("Chameleon", "Herbie Hancock", "Jazz", "EN", "4Ce66JznW8QbeyTdSzdGwR"),
            new TrackData("Cross Road Blues", "Robert Johnson", "Blues", "EN", "1TrGdXSgiBm8W68D2K1COG"),
            new TrackData("Hoochie Coochie Man", "Muddy Waters", "Blues", "EN", "5F7qmHHRIsYxnVZttf4cr9"),
            new TrackData("Pride and Joy", "Stevie Ray Vaughan", "Blues", "EN", "1a2iF9XymafjRk56q7oCxo"),
            new TrackData("Layla", "Eric Clapton", "Blues", "EN", "0g4kJXE7uV4PkFUWw4wLe8"),
            new TrackData("The Thrill Is Gone", "B.B. King", "Blues", "EN", "4NQfrmGs9iQXVQI9IpRhjM"),
            new TrackData("At Last", "Etta James", "Blues", "EN", "4Hhv2vrOTy89HFRcjU3QOx"),
            new TrackData("Bad and Boujee", "Migos", "Trap", "EN", "46DHEGYNLX4KwmmcAtItAN"),
            new TrackData("Réseaux", "Niska", "Trap", "FR", "0HKNlsZ0zFlSqUqWj1nVVG"),
            new TrackData("Mask Off", "Future", "Trap", "EN", "0VgkVdmE4gld66l8iyGjgx"),
            new TrackData("Désaccordé", "Vald", "Trap", "FR", "4ueL4j6iOPX23UG5VsvGrm"),
            new TrackData("Soul Survivor", "Jeezy", "Trap", "EN", "12bZjnzjuJoyRslVC4BmxU"),
            new TrackData("Zoo", "Kaaris", "Trap", "FR", "3BaGyruhFKKP6f8e2koRri"),
            new TrackData("Petit frère", "IAM", "Boom Bap", "FR", "3jqBLwtTiUYismDiZgAgPE"),
            new TrackData("C.R.E.A.M.", "Wu-Tang Clan", "Boom Bap", "EN", "119c93MHjrDLJTApCVGpvx"),
            new TrackData("Rêve d'avoir des rêves", "Nekfeu", "Boom Bap", "FR", "3LOWSpsDZq3eTHLJdxtBXi"),
            new TrackData("L'enfant seul", "Oxmo Puccino", "Boom Bap", "FR", "2yeDHbtDaEnWD44U0ULEu3"),
            new TrackData("Shook Ones Pt II", "Mobb Deep", "Boom Bap", "EN", "33ZXjLCpiINn8eQIDYEPTD"),
            new TrackData("Juicy", "The Notorious B.I.G.", "Boom Bap", "EN", "2AP7m2dBb8ULTx4Gc1rdMc"),
            new TrackData("Still D.R.E.", "Dr. Dre", "West Coast", "EN", "503OTo2dSqe7qk76rgsbep"),
            new TrackData("Gin and Juice", "Snoop Dogg", "West Coast", "EN", "39QBkWKnap8wRSW4WB9OK0"),
            new TrackData("It Was a Good Day", "Ice Cube", "West Coast", "EN", "2GrOq1y5gksYrqkc8Jzl9T"),
            new TrackData("California Love", "2Pac", "West Coast", "EN", "0LyLz6XsVs6wz85dK0S6EG"),
            new TrackData("HUMBLE.", "Kendrick Lamar", "West Coast", "EN", "7KXjTSCq5nL1LoYtL7XAwS"),
            new TrackData("Jsuis PNL", "PNL", "Cloud Rap", "FR", "74w7XGtFixyI8w1vTHjfcL"),
            new TrackData("Laboratoire", "Werenoi", "Cloud Rap", "FR", "7wOgZ1otoKYDvKyQKP1sNZ"),
            new TrackData("L$D", "A$AP Rocky", "Cloud Rap", "EN", "4S7YHmlWwfwArgd8LfSPud"),
            new TrackData("goosebumps", "Travis Scott", "Cloud Rap", "EN", "6gBFPUFcJLzWGx4lenP6h2"),
            new TrackData("Intro", "Josman", "Cloud Rap", "FR", "6qvyN6NTUpdfOJRYjtSSd7"),
            new TrackData("Ginseng Strip 2002", "Yung Lean", "Cloud Rap", "EN", "7v3rmoy5jcn4h5UqwQyCM3")
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
            new GenreMoodData("Rock", "Epic"),
            new GenreMoodData("Boom Bap", "Chill"),
            new GenreMoodData("Boom Bap", "Melancholic"),
            new GenreMoodData("West Coast", "Chill"),
            new GenreMoodData("West Coast", "Happy"),
            new GenreMoodData("Trap", "Energetic"),
            new GenreMoodData("Trap", "Aggressive"),
            new GenreMoodData("Cloud Rap", "Dreamy"),
            new GenreMoodData("Cloud Rap", "Chill"),
            new GenreMoodData("Cloud Rap", "Melancholic"),
            new GenreMoodData("French house", "Happy"),
            new GenreMoodData("French house", "Energetic"),
            new GenreMoodData("EDM", "Energetic"),
            new GenreMoodData("EDM", "Happy"),
            new GenreMoodData("Lo-Fi", "Chill"),
            new GenreMoodData("Lo-Fi", "Melancholic"),
            new GenreMoodData("Lo-Fi", "Dreamy"),
            new GenreMoodData("Jazz", "Chill"),
            new GenreMoodData("Jazz", "Melancholic"),
            new GenreMoodData("Jazz", "Dreamy"),
            new GenreMoodData("Blues", "Melancholic"),
            new GenreMoodData("Blues", "Chill")

    };

    public static ArtistGenreData[] ARTIST_GENRE = {
            new ArtistGenreData("Daft Punk", "French house"),
            new ArtistGenreData("Justice", "French house"),
            new ArtistGenreData("Alan Braxe", "French house"),
            new ArtistGenreData("Modjo", "French house"),
            new ArtistGenreData("Cassius", "French house"),
            new ArtistGenreData("Maxtron", "French house"),
            new ArtistGenreData("David Guetta", "EDM"),
            new ArtistGenreData("DJ Snake", "EDM"),
            new ArtistGenreData("Calvin Harris", "EDM"),
            new ArtistGenreData("Alan Walker", "EDM"),
            new ArtistGenreData("Avicii", "EDM"),
            new ArtistGenreData("Martin Garrix", "EDM"),
            new ArtistGenreData("Kavinsky", "Synthwave"),
            new ArtistGenreData("Timecop1983", "Synthwave"),
            new ArtistGenreData("The Midnight", "Synthwave"),
            new ArtistGenreData("Neon Nox", "Synthwave"),
            new ArtistGenreData("Scandroid", "Synthwave"),
            new ArtistGenreData("Deep Beyond", "Synthwave"),
            new ArtistGenreData("Nujabes", "Lo-Fi"),
            new ArtistGenreData("J Dilla", "Lo-Fi"),
            new ArtistGenreData("Jinsang", "Lo-Fi"),
            new ArtistGenreData("idealism", "Lo-Fi"),
            new ArtistGenreData("Tomppabeats", "Lo-Fi"),
            new ArtistGenreData("eevee", "Lo-Fi"),
            new ArtistGenreData("Duke Ellington", "Jazz"),
            new ArtistGenreData("Charlie Parker", "Jazz"),
            new ArtistGenreData("Miles Davis", "Jazz"),
            new ArtistGenreData("Art Blakey", "Jazz"),
            new ArtistGenreData("Ornette Coleman", "Jazz"),
            new ArtistGenreData("Herbie Hancock", "Jazz"),
            new ArtistGenreData("Robert Johnson", "Blues"),
            new ArtistGenreData("Muddy Waters", "Blues"),
            new ArtistGenreData("Stevie Ray Vaughan", "Blues"),
            new ArtistGenreData("Eric Clapton", "Blues"),
            new ArtistGenreData("B.B. King", "Blues"),
            new ArtistGenreData("Etta James", "Blues"),
            new ArtistGenreData("Migos", "Trap"),
            new ArtistGenreData("Niska", "Trap"),
            new ArtistGenreData("Future", "Trap"),
            new ArtistGenreData("Vald", "Trap"),
            new ArtistGenreData("Jeezy", "Trap"),
            new ArtistGenreData("Kaaris", "Trap"),
            new ArtistGenreData("IAM", "Boom Bap"),
            new ArtistGenreData("Wu-Tang Clan", "Boom Bap"),
            new ArtistGenreData("Nekfeu", "Boom Bap"),
            new ArtistGenreData("Oxmo Puccino", "Boom Bap"),
            new ArtistGenreData("Mobb Deep", "Boom Bap"),
            new ArtistGenreData("The Notorious B.I.G.", "Boom Bap"),
            new ArtistGenreData("Dr. Dre", "West Coast"),
            new ArtistGenreData("Snoop Dogg", "West Coast"),
            new ArtistGenreData("Ice Cube", "West Coast"),
            new ArtistGenreData("2Pac", "West Coast"),
            new ArtistGenreData("Kendrick Lamar", "West Coast"),
            new ArtistGenreData("PNL", "Cloud Rap"),
            new ArtistGenreData("Werenoi", "Cloud Rap"),
            new ArtistGenreData("A$AP Rocky", "Cloud Rap"),
            new ArtistGenreData("Travis Scott", "Cloud Rap"),
            new ArtistGenreData("Josman", "Cloud Rap"),
            new ArtistGenreData("Yung Lean", "Cloud Rap"),
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
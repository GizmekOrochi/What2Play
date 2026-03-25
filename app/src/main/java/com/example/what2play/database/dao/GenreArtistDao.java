package com.example.what2play.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.GenreArtist;

import java.util.List;

@Dao
public interface GenreArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(GenreArtist relation);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(GenreArtist... relations);

    @Query("SELECT * FROM genres INNER JOIN genre_artist ON genres.id = genre_artist.genreId WHERE genre_artist.artistId = :artistId")
    List<Genre> getGenresForArtist(int artistId);

    @Query("SELECT artists.* FROM artists INNER JOIN genre_artist ON artists.id = genre_artist.artistId WHERE genre_artist.genreId = :genreId")
    List<Artist> getArtistsForGenre(int genreId);

    @Query("DELETE FROM genre_artist")
    void clear();
}
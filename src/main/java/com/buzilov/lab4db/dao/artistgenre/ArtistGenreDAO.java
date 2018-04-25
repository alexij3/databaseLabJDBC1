package com.buzilov.lab4db.dao.artistgenre;

import com.buzilov.lab4db.model.ArtistGenre;
import com.buzilov.lab4db.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface ArtistGenreDAO {
    ArtistGenre get(int id) throws SQLException;
    ArtistGenre update(ArtistGenre artistGenre) throws SQLException;
    void delete(int id, Genre genre) throws SQLException;
    ArtistGenre insert(ArtistGenre artistGenre) throws SQLException;
    List<ArtistGenre> getAll() throws SQLException;
}

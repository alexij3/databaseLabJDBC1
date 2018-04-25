package com.buzilov.lab4db.service.artist;

import com.buzilov.lab4db.model.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ArtistService {
    Artist insertArtist(Artist artist) throws SQLException;
    Artist getArtist(int id) throws SQLException;
    Artist updateArtist(Artist artist) throws SQLException;
    void deleteArtist(int id) throws SQLException;
    List<Artist> getAll() throws SQLException;
}

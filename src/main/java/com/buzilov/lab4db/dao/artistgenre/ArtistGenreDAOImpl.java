package com.buzilov.lab4db.dao.artistgenre;

import com.buzilov.lab4db.dao.artist.ArtistDAOImpl;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.ArtistGenre;
import com.buzilov.lab4db.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistGenreDAOImpl implements ArtistGenreDAO {
    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Autowired
    ArtistDAOImpl artistDAO;

    Connection con;
    Statement statement;

    @Override
    public ArtistGenre get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArtistGenre update(String oldGenre, ArtistGenre artistGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateArtistGenre = "UPDATE artist_and_genre SET genre = ? WHERE id_artist = ? AND genre = ?";
        update = con.prepareStatement(updateArtistGenre);
        update.setString(1, artistGenre.getGenre().toString());
        update.setInt(2, artistGenre.getArtistId());
        update.setString(3, oldGenre);
        System.out.println(update.executeUpdate());

        con.close();
        return artistGenre;
    }

    @Override
    public void delete(int id, Genre genre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteArtistGenre = "DELETE FROM artist_and_genre WHERE id_artist = ? AND genre = ?";
        delete = con.prepareStatement(deleteArtistGenre);
        delete.setInt(1, id);
        delete.setString(2, genre.toString());
        delete.executeUpdate();

        con.close();
    }

    @Override
    public synchronized ArtistGenre insert(ArtistGenre artistGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        System.out.println(artistGenre.getArtistId());

        PreparedStatement insert;
        String insertArtistGenre = "INSERT INTO artist_and_genre (id_artist, genre) VALUES (?, ?)";
        insert = con.prepareStatement(insertArtistGenre);
        insert.setInt(1, artistGenre.getArtistId());
        insert.setString(2, artistGenre.getGenre().toString());
        insert.executeUpdate();

        con.close();
        return artistGenre;
    }

    @Override
    public synchronized List<ArtistGenre> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ArtistGenre> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT id_artist, genre FROM artist_and_genre");

        while (rs.next()){
            list.add(new ArtistGenre(rs.getInt("id_artist"), Genre.valueOf(rs.getString("genre"))));
        }

        List <Artist> artists = artistDAO.getAll();
        for (ArtistGenre artistGenre : list)
            artistGenre.setArtist(artists.stream().filter(el->el.getId() == artistGenre.getArtistId()).findFirst().orElse(null));

        statement.close();
        con.close();
        return list;
    }
}

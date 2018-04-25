package com.buzilov.lab4db.dao.artist;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class ArtistDAOImpl implements ArtistDAO{
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public Artist insertArtist(Artist artist) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement insert;
        String insertArtist = "INSERT INTO artist (id, name) VALUES (?, ?)";
        insert = con.prepareStatement(insertArtist);
        insert.setInt(1, artist.getId());
        insert.setString(2, artist.getName());
        insert.executeUpdate();

        con.close();
        return artist;
    }

    @Override
    public Artist getArtist(int id) throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getArtist = "SELECT * FROM artist WHERE id = ?";
        get = con.prepareStatement(getArtist);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        Artist artist = null;
        if (rs.next()){
            artist = new Artist(rs.getInt("id"), rs.getString("name"));
        }

        con.close();
        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist) throws  SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateArtist = "UPDATE artist SET name = ? where id = ?";
        update = con.prepareStatement(updateArtist);
        update.setString(1, artist.getName());
        update.setInt(2, artist.getId());
        update.executeUpdate();

        con.close();
        return artist;
    }

    @Override
    public void deleteArtist(int id) throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteArtist = "DELETE FROM artist WHERE id = ?";
        delete = con.prepareStatement(deleteArtist);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<Artist> getAll() throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<Artist> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM artist");

        while (rs.next()){
            list.add(new Artist(rs.getInt("id"), rs.getString("name")));
        }

        con.close();
        statement.close();
        return list;
    }
}

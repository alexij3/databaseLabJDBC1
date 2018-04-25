package com.buzilov.lab4db.dao.artistandimpresario;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.ArtistAndImpresario;
import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.model.Impresario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistAndImpresarioDAOImpl implements ArtistAndImpresarioDAO{
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public ArtistAndImpresario insert(ArtistAndImpresario artistAndImpresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertArtistAndImpresario = "INSERT INTO artist_and_impresario (id_artist, id_impresario) VALUES (?, ?)";
        insert = con.prepareStatement(insertArtistAndImpresario);
        insert.setInt(1, artistAndImpresario.getArtist().getId());
        insert.setInt(2, artistAndImpresario.getImpresario().getId());
        insert.executeUpdate();

        con.close();
        return artistAndImpresario;
    }

    @Override
    public ArtistAndImpresario get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArtistAndImpresario update(ArtistAndImpresario artistAndImpresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        System.out.println(artistAndImpresario.getArtist().getId());
        System.out.println(artistAndImpresario.getImpresario().toString());
        PreparedStatement update;
        String updateArtistAndImpresario = "UPDATE artist_and_impresario SET id_impresario = ? WHERE id_artist = ?";
        update = con.prepareStatement(updateArtistAndImpresario);
        update.setInt(1, artistAndImpresario.getImpresario().getId());
        update.setInt(2, artistAndImpresario.getArtist().getId());
        System.out.println(update.executeUpdate());

        con.close();
        return artistAndImpresario;
    }

    @Override
    public void delete(int id, int impresarioId) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteArtistAndImpresario = "DELETE FROM artist_and_impresario WHERE id_artist = ? AND id_impresario = ?";
        delete = con.prepareStatement(deleteArtistAndImpresario);
        delete.setInt(1, id);
        delete.setInt(2, impresarioId);
        delete.executeUpdate();

        con.close();
    }

    @Override
    public List<ArtistAndImpresario> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ArtistAndImpresario> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT artist.id, artist.name, impresario.id, impresario.name" +
                                                    " FROM artist LEFT JOIN artist_and_impresario ON artist.id = artist_and_impresario.id_artist" +
                                                    " LEFT JOIN impresario ON artist_and_impresario.id_impresario = impresario.id");

        while (rs.next()){
            list.add(new ArtistAndImpresario(new Artist(rs.getInt("artist.id"), rs.getString("artist.name")),
                     new Impresario(rs.getInt("impresario.id"), rs.getString("impresario.name"))));
        }

        statement.close();
        con.close();
        return list;
    }
}

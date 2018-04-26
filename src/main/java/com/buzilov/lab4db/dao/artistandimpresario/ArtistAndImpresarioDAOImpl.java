package com.buzilov.lab4db.dao.artistandimpresario;

import com.buzilov.lab4db.dao.artist.ArtistDAOImpl;
import com.buzilov.lab4db.dao.impresario.ImpresarioDAOImpl;
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
    DataStorageJdbc dataStorageJdbc;

    @Autowired
    ArtistDAOImpl artistDAO;

    @Autowired
    ImpresarioDAOImpl impresarioDAO;

    Connection con;
    Statement statement;

    @Override
    public ArtistAndImpresario insert(ArtistAndImpresario artistAndImpresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertArtistAndImpresario = "INSERT INTO artist_and_impresario (id_artist, id_impresario) VALUES (?, ?)";
        insert = con.prepareStatement(insertArtistAndImpresario);
        insert.setInt(1, artistAndImpresario.getArtistId());
        insert.setInt(2, artistAndImpresario.getImpresarioId());
        insert.executeUpdate();

        con.close();
        return artistAndImpresario;
    }

    @Override
    public ArtistAndImpresario get(int id) throws SQLException {
        return null;
    }

    @Override
    public synchronized ArtistAndImpresario update(int oldImpresarioId, ArtistAndImpresario artistAndImpresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateArtistAndImpresario = "UPDATE artist_and_impresario SET id_impresario = ? WHERE id_artist = ? AND id_impresario = ?";
        update = con.prepareStatement(updateArtistAndImpresario);
        update.setInt(1, artistAndImpresario.getImpresarioId());
        update.setInt(2, artistAndImpresario.getArtistId());
        update.setInt(3, oldImpresarioId);
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
    public synchronized List<ArtistAndImpresario> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ArtistAndImpresario> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT id_artist, id_impresario FROM artist_and_impresario");

        while (rs.next()){
            list.add(new ArtistAndImpresario(rs.getInt("id_artist"), rs.getInt("id_impresario")));
        }

        List <Artist> artists = artistDAO.getAll();
        List <Impresario> impresarios = impresarioDAO.getAll();

        for (ArtistAndImpresario a : list){
            a.setArtist(artists.stream().filter(el->el.getId() == a.getArtistId()).findFirst().orElse(null));
            a.setImpresario(impresarios.stream().filter(el->el.getId() == a.getImpresarioId()).findFirst().orElse(null));
        }

        statement.close();
        con.close();
        return list;
    }
}

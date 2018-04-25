package com.buzilov.lab4db.dao.impresariogenre;

import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.model.Impresario;
import com.buzilov.lab4db.model.ImpresarioGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImpresarioGenreDAOImpl implements ImpresarioGenreDAO {
    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public ImpresarioGenre get(int id) throws SQLException {
        return null;
    }

    @Override
    public ImpresarioGenre update(ImpresarioGenre impresarioGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        System.out.println(impresarioGenre.getImpresario().getId());
        System.out.println(impresarioGenre.getGenre().toString());
        PreparedStatement update;
        String updateImpresarioGenre = "UPDATE impresario_and_genre SET genre = ? WHERE id_impresario = ?";
        update = con.prepareStatement(updateImpresarioGenre);
        update.setString(1, impresarioGenre.getGenre().toString());
        update.setInt(2, impresarioGenre.getImpresario().getId());
        System.out.println(update.executeUpdate());

        con.close();
        return impresarioGenre;
    }

    @Override
    public void delete(int id, Genre genre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteImpresarioGenre = "DELETE FROM impresario_and_genre WHERE id_impresario = ? AND genre = ?";
        delete = con.prepareStatement(deleteImpresarioGenre);
        delete.setInt(1, id);
        delete.setString(2, genre.toString());
        delete.executeUpdate();

        con.close();
    }

    @Override
    public ImpresarioGenre insert(ImpresarioGenre impresarioGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertImpresarioGenre = "INSERT INTO impresario_and_genre (id_impresario, genre) VALUES (?, ?)";
        insert = con.prepareStatement(insertImpresarioGenre);
        insert.setInt(1, impresarioGenre.getImpresario().getId());
        insert.setString(2, impresarioGenre.getGenre().toString());
        insert.executeUpdate();

        con.close();
        return impresarioGenre;
    }

    @Override
    public List<ImpresarioGenre> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ImpresarioGenre> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT impresario.id, name, genre FROM impresario_and_genre RIGHT OUTER JOIN impresario\n" +
                "ON impresario_and_genre.id_impresario = impresario.id");

        while (rs.next()){
            list.add(new ImpresarioGenre(new Impresario(rs.getInt("impresario.id"), rs.getString("name"))));
            if (rs.getString("genre") != null) list.get(list.size()-1).setGenre(Genre.valueOf(rs.getString("genre")));
        }

        statement.close();
        con.close();
        return list;
    }
}

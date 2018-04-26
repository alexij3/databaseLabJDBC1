package com.buzilov.lab4db.dao.impresariogenre;

import com.buzilov.lab4db.dao.impresario.ImpresarioDAOImpl;
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

    @Autowired
    ImpresarioDAOImpl impresarioDAO;

    Connection con;
    Statement statement;

    @Override
    public ImpresarioGenre get(int id) throws SQLException {
        return null;
    }

    @Override
    public ImpresarioGenre update(String oldGenre, ImpresarioGenre impresarioGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateImpresarioGenre = "UPDATE impresario_and_genre SET genre = ? WHERE id_impresario = ? AND genre = ?";
        update = con.prepareStatement(updateImpresarioGenre);
        update.setString(1, impresarioGenre.getGenre().toString());
        update.setInt(2, impresarioGenre.getImpresarioId());
        update.setString(3, oldGenre);
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
    public synchronized ImpresarioGenre insert(ImpresarioGenre impresarioGenre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        System.out.println(impresarioGenre.getImpresarioId());

        PreparedStatement insert;
        String insertImpresarioGenre = "INSERT INTO impresario_and_genre (id_impresario, genre) VALUES (?, ?)";
        insert = con.prepareStatement(insertImpresarioGenre);
        insert.setInt(1, impresarioGenre.getImpresarioId());
        insert.setString(2, impresarioGenre.getGenre().toString());
        insert.executeUpdate();

        con.close();
        return impresarioGenre;
    }

    @Override
    public synchronized List<ImpresarioGenre> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ImpresarioGenre> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT id_impresario, genre FROM impresario_and_genre");

        while (rs.next()){
            list.add(new ImpresarioGenre(rs.getInt("id_impresario"), Genre.valueOf(rs.getString("genre"))));
        }

        List <Impresario> impresarios = impresarioDAO.getAll();
        for (ImpresarioGenre impresarioGenre : list)
            impresarioGenre.setImpresario(impresarios.stream().filter(el->el.getId() == impresarioGenre.getImpresarioId()).findFirst().orElse(null));

        statement.close();
        con.close();
        return list;
    }
}

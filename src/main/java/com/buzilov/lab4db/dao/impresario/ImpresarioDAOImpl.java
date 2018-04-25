package com.buzilov.lab4db.dao.impresario;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Impresario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImpresarioDAOImpl implements ImpresarioDAO{
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public Impresario insert(Impresario impresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertImpresario = "INSERT INTO impresario (name) VALUES (?)";
        insert = con.prepareStatement(insertImpresario);
        insert.setString(1, impresario.getName());
        insert.executeUpdate();

        con.close();
        return impresario;
    }

    @Override
    public Impresario get(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getImpresario = "SELECT * FROM impresario WHERE id = ?";
        get = con.prepareStatement(getImpresario);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        Impresario impresario = null;
        if (rs.next()){
            impresario = new Impresario(rs.getInt("id"), rs.getString("name"));
        }

        con.close();
        return impresario;
    }

    @Override
    public Impresario update(Impresario impresario) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateImpresario = "UPDATE impresario SET name = ? where id = ?";
        update = con.prepareStatement(updateImpresario);
        update.setString(1, impresario.getName());
        update.setInt(2, impresario.getId());
        update.executeUpdate();

        con.close();
        return impresario;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteImpresario = "DELETE FROM impresario WHERE id = ?";
        delete = con.prepareStatement(deleteImpresario);
        delete.setInt(1, id);
        delete.executeUpdate();

        con.close();
    }

    @Override
    public List<Impresario> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<Impresario> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM impresario");

        while (rs.next()){
            list.add(new Impresario(rs.getInt("id"), rs.getString("name")));
        }

        con.close();
        statement.close();
        return list;
    }
}

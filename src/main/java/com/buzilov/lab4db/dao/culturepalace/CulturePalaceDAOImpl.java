package com.buzilov.lab4db.dao.culturepalace;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.CulturePalace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CulturePalaceDAOImpl implements CulturePalaceDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public CulturePalace insert(CulturePalace culturePalace) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertMovie = "INSERT INTO culture_palace (name, address, capacity) VALUES (?, ?, ?)";
        insert = con.prepareStatement(insertMovie);

        insert.setString(1, culturePalace.getName());
        insert.setString(2, culturePalace.getAddress());
        insert.setInt(3, culturePalace.getCapacity());
        insert.executeUpdate();

        con.close();
        return culturePalace;
    }

    @Override
    public CulturePalace get(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getCulturePalace = "SELECT * FROM culture_palace WHERE id = ?";
        get = con.prepareStatement(getCulturePalace);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        CulturePalace culturePalace = null;
        if (rs.next()){
            culturePalace = new CulturePalace(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                    rs.getInt("capacity"));
        }

        con.close();
        return culturePalace;
    }

    @Override
    public CulturePalace update(CulturePalace culturePalace) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateCulturePalace = "UPDATE culture_palace SET name = ?, address = ?, capacity = ? WHERE id = ?";
        System.out.println(culturePalace);
        update = con.prepareStatement(updateCulturePalace);
        update.setString(1, culturePalace.getName());
        update.setString(2, culturePalace.getAddress());
        update.setInt(3, culturePalace.getCapacity());
        update.setInt(4, culturePalace.getId());
        update.executeUpdate();

        con.close();
        return culturePalace;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteCulturePalace = "DELETE FROM culture_palace WHERE id = ?";
        delete = con.prepareStatement(deleteCulturePalace);
        delete.setInt(1, id);
        delete.executeUpdate();

        con.close();
    }

    @Override
    public List<CulturePalace> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<CulturePalace> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM culture_palace");

        while (rs.next()){
            list.add(new CulturePalace(rs.getInt("id"), rs.getString("name"),
                    rs.getString("address"), rs.getInt("capacity")));
        }

        con.close();
        statement.close();
        return list;
    }
}

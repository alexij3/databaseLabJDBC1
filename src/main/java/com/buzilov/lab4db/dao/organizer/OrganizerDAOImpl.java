package com.buzilov.lab4db.dao.organizer;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizerDAOImpl implements OrganizerDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public Organizer insert(Organizer organizer) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertOrganizer = "INSERT INTO organizer (name) VALUES (?)";
        insert = con.prepareStatement(insertOrganizer);
        insert.setString(1, organizer.getName());
        insert.executeUpdate();

        con.close();
        return organizer;
    }

    @Override
    public Organizer get(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getOrganizer = "SELECT * FROM organizer WHERE id = ?";
        get = con.prepareStatement(getOrganizer);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        Organizer organizer = null;
        if (rs.next()){
            organizer = new Organizer(rs.getInt("id"), rs.getString("name"));
        }

        con.close();
        return organizer;
    }

    @Override
    public Organizer update(Organizer organizer) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateOrganizer = "UPDATE organizer SET name = ? where id = ?";
        update = con.prepareStatement(updateOrganizer);
        update.setString(1, organizer.getName());
        update.setInt(2, organizer.getId());
        update.executeUpdate();

        con.close();
        return organizer;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteOrganizer = "DELETE FROM organizer WHERE id = ?";
        delete = con.prepareStatement(deleteOrganizer);
        delete.setInt(1, id);
        delete.executeUpdate();

        con.close();
    }

    @Override
    public List<Organizer> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<Organizer> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM organizer");

        while (rs.next()){
            list.add(new Organizer(rs.getInt("id"), rs.getString("name")));
        }

        con.close();
        statement.close();
        return list;
    }
}

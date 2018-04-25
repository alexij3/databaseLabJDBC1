package com.buzilov.lab4db.dao.theatre;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TheatreDAOImpl implements TheatreDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public Theatre insert(Theatre theatre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertTheatre = "INSERT INTO theatre (name, address, capacity) VALUES (?, ?, ?)";
        insert = con.prepareStatement(insertTheatre);

        insert.setString(1, theatre.getName());
        insert.setString(2, theatre.getAddress());
        insert.setInt(3, theatre.getCapacity());
        insert.executeUpdate();

        con.close();
        return theatre;
    }

    @Override
    public Theatre get(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getTheatre = "SELECT * FROM theatre WHERE id = ?";
        get = con.prepareStatement(getTheatre);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        Theatre theatre = null;
        if (rs.next()){
            theatre = new Theatre(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                    rs.getInt("capacity"));
        }

        con.close();
        return theatre;
    }

    @Override
    public Theatre update(Theatre theatre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateTheatre = "UPDATE theatre SET name = ?, address = ?, capacity = ? WHERE id = ?";
        System.out.println(theatre);
        update = con.prepareStatement(updateTheatre);
        update.setString(1, theatre.getName());
        update.setString(2, theatre.getAddress());
        update.setInt(3, theatre.getCapacity());
        update.setInt(4, theatre.getId());
        update.executeUpdate();

        con.close();
        return theatre;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement delete;
        String deleteTheatre = "DELETE FROM theatre WHERE id = ?";
        delete = con.prepareStatement(deleteTheatre);
        delete.setInt(1, id);
        delete.executeUpdate();

        con.close();
    }

    @Override
    public List<Theatre> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<Theatre> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM theatre");

        while (rs.next()){
            list.add(new Theatre(rs.getInt("id"), rs.getString("name"),
                    rs.getString("address"), rs.getInt("capacity")));
        }

        con.close();
        statement.close();
        return list;
    }
}

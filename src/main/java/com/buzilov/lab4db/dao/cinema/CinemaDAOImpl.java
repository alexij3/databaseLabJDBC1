package com.buzilov.lab4db.dao.cinema;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CinemaDAOImpl implements CinemaDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;


    @Override
    public Cinema insertCinema(Cinema cinema) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertCinema = "INSERT INTO cinema (id, name, address, screen_size) VALUES (?, ?, ?, ?)";
        insert = con.prepareStatement(insertCinema);
        insert.setInt(1, cinema.getId());
        insert.setString(2, cinema.getName());
        insert.setString(3, cinema.getAddress());
        insert.setInt(4, cinema.getScreenSize());
        insert.executeUpdate();

        con.close();
        return cinema;
    }

    @Override
    public Cinema getCinema(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getCinema = "SELECT * FROM cinema WHERE id = ?";
        get = con.prepareStatement(getCinema);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        Cinema cinema = null;
        if (rs.next()){
            cinema = new Cinema(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                    rs.getInt("screen_size"));
        }

        con.close();
        return cinema;
    }

    @Override
    public Cinema updateCinema(Cinema cinema) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement update;
        String updateCinema = "UPDATE cinema SET name = ?, address = ?, screen_size = ? WHERE id = ?";
        update = con.prepareStatement(updateCinema);
        update.setString(1, cinema.getName());
        update.setString(2, cinema.getAddress());
        update.setInt(3, cinema.getScreenSize());
        update.setInt(4, cinema.getId());
        update.executeUpdate();

        con.close();
        return cinema;
    }

    @Override
    public void deleteCinema(int id) throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteCinema = "DELETE FROM cinema WHERE id = ?";
        delete = con.prepareStatement(deleteCinema);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<Cinema> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<Cinema> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM cinema");

        while (rs.next()){
            list.add(new Cinema(rs.getInt("id"), rs.getString("name"),
                    rs.getString("address"), rs.getInt("screen_size")));
        }

        con.close();
        statement.close();
        return list;
    }
}

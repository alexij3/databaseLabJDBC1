package com.buzilov.lab4db.dao.concerthall;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.ConcertHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConcertHallDAOImpl implements ConcertHallDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public ConcertHall insert(ConcertHall concertHall) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertMovie = "INSERT INTO concert_hall (name, address, capacity) VALUES (?, ?, ?)";
        insert = con.prepareStatement(insertMovie);

        insert.setString(1, concertHall.getName());
        insert.setString(2, concertHall.getAddress());
        insert.setInt(3, concertHall.getCapacity());
        insert.executeUpdate();

        con.close();
        return concertHall;
    }

    @Override
    public ConcertHall get(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement get;
        String getConcertHall = "SELECT * FROM concert_hall WHERE id = ?";
        get = con.prepareStatement(getConcertHall);
        get.setInt(1, id);
        ResultSet rs = get.executeQuery();
        ConcertHall concertHall = null;
        if (rs.next()){
            concertHall = new ConcertHall(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                    rs.getInt("capacity"));
        }

        con.close();
        return concertHall;
    }

    @Override
    public ConcertHall update(ConcertHall concertHall)throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateConcertHall = "UPDATE concert_hall SET name = ?, address = ?, capacity = ? WHERE id = ?";
        System.out.println(concertHall);
        update = con.prepareStatement(updateConcertHall);
        update.setString(1, concertHall.getName());
        update.setString(2, concertHall.getAddress());
        update.setInt(3, concertHall.getCapacity());
        update.setInt(4, concertHall.getId());
        update.executeUpdate();

        con.close();
        return concertHall;
    }

    @Override
    public void delete(int id)throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteConcertHall = "DELETE FROM concert_hall WHERE id = ?";
        delete = con.prepareStatement(deleteConcertHall);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<ConcertHall> getAll() throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();
        List<ConcertHall> list = new ArrayList<>();
        ResultSet rs = dataStorageJdbc.executeQuery("SELECT * FROM concert_hall");

        while (rs.next()){
            list.add(new ConcertHall(rs.getInt("id"), rs.getString("name"),
                    rs.getString("address"), rs.getInt("capacity")));
        }

        con.close();
        statement.close();
        return list;
    }
}

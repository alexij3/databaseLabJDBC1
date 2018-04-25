package com.buzilov.lab4db.dao.concertinhall;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.ConcertHall;
import com.buzilov.lab4db.model.ConcertInHall;
import com.buzilov.lab4db.model.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConcertInHallDAOImpl implements ConcertInHallDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public ConcertInHall insert(ConcertInHall concertInHall)  throws SQLException {
        System.out.println("insert concerthall " + concertInHall);
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertMovie = "INSERT INTO concert_in_hall (id, id_concert_hall, name, id_organizer, date) VALUES (?, ?, ?, ?, ?)";
        insert = con.prepareStatement(insertMovie);
        insert.setInt(1, concertInHall.getId());
        insert.setInt(2, concertInHall.getConcertHall().getId());
        insert.setString(3, concertInHall.getName());
        insert.setInt(4, concertInHall.getOrganizer().getId());
        insert.setDate(5, java.sql.Date.valueOf(concertInHall.getDate()));
        insert.executeUpdate();

        con.close();
        return concertInHall;
    }

    @Override
    public ConcertInHall get(int id) {
        return null;
    }

    @Override
    public ConcertInHall update(ConcertInHall concertInHall)  throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateConcertInHall = "UPDATE concert_in_hall SET id_concert_hall = ?, name = ?, id_organizer = ?, date = ? where id = ?";
        System.out.println(concertInHall);
        update = con.prepareStatement(updateConcertInHall);
        update.setInt(1, concertInHall.getConcertHall().getId());
        update.setString(2, concertInHall.getName());
        update.setInt(3, concertInHall.getOrganizer().getId());
        update.setDate(4, java.sql.Date.valueOf(concertInHall.getDate()));
        update.setInt(5, concertInHall.getId());
        update.executeUpdate();

        con.close();
        return concertInHall;
    }

    @Override
    public void delete(int id)  throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteMovie = "DELETE FROM cinema_movie WHERE id_movie = ?";
        delete = con.prepareStatement(deleteMovie);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<ConcertInHall> getAll()  throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<ConcertInHall> list = new ArrayList<>();
        ResultSet rs = this.executeQuery("SELECT concert_in_hall.id, concert_in_hall.name, id_concert_hall, concert_hall.name, concert_hall.address, concert_hall.capacity, concert_in_hall.name " +
                ", id_organizer, organizer.name, date FROM concert_in_hall" +
                "\nJOIN concert_hall ON concert_hall.id = id_concert_hall" +
                "\nJOIN organizer ON id_organizer = organizer.id");

        while (rs.next()){
            list.add(new ConcertInHall(rs.getInt("id"), new ConcertHall(rs.getInt("id_concert_hall"),
                    rs.getString("concert_hall.name"), rs.getString("concert_hall.address"),
                    rs.getInt("concert_hall.capacity")), rs.getInt("id_concert_hall"), rs.getString("concert_in_hall.name"), new Organizer(
                            rs.getInt("id_organizer"), rs.getString("organizer.name")), rs.getInt("id_organizer"),
                    rs.getDate("date").toLocalDate()));
        }

        con.close();
        statement.close();
        return list;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException{
        return  statement.executeUpdate(query);
    }
}

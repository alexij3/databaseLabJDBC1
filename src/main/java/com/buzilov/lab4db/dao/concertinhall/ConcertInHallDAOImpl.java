package com.buzilov.lab4db.dao.concertinhall;

import com.buzilov.lab4db.dao.concerthall.ConcertHallDAOImpl;
import com.buzilov.lab4db.dao.organizer.OrganizerDAOImpl;
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

    @Autowired
    OrganizerDAOImpl organizerDAO;

    @Autowired
    ConcertHallDAOImpl concertHallDAO;

    Connection con;
    Statement statement;

    @Override
    public ConcertInHall insert(ConcertInHall concertInHall) throws SQLException {
        System.out.println("insert concertHall " + concertInHall);
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertConcertInHall = "INSERT INTO concert_in_hall (id_concert_hall, name, id_organizer, date) VALUES (?, ?, ?, ?)";
        insert = con.prepareStatement(insertConcertInHall);
        insert.setInt(1, concertInHall.getConcertHallId());
        insert.setString(2, concertInHall.getName());
        insert.setInt(3, concertInHall.getOrganizerId());
        insert.setDate(4, java.sql.Date.valueOf(concertInHall.getDate()));
        insert.executeUpdate();

        con.close();
        return concertInHall;
    }

    @Override
    public ConcertInHall get(int id) {
        return null;
    }

    @Override
    public ConcertInHall update(ConcertInHall concertHall) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateConcertInHall = "UPDATE concert_in_hall SET id_concert_hall = ?, name = ?, id_organizer = ?, date = ? where id = ?";
        System.out.println(concertHall);
        update = con.prepareStatement(updateConcertInHall);
        update.setInt(1, concertHall.getConcertHallId());
        update.setString(2, concertHall.getName());
        update.setInt(3, concertHall.getOrganizerId());
        update.setDate(4, java.sql.Date.valueOf(concertHall.getDate()));
        update.setInt(5, concertHall.getId());
        update.executeUpdate();

        con.close();
        return concertHall;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteConcertInHall = "DELETE FROM concert_in_hall WHERE id = ?";
        delete = con.prepareStatement(deleteConcertInHall);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<ConcertInHall> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List <ConcertInHall> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT *" +
                " FROM concert_in_hall");

        while (rs.next()){
            list.add(new ConcertInHall(rs.getInt("id"),
                    rs.getInt("id_concert_hall"), rs.getString("name"), rs.getInt("id_organizer"),
                    rs.getDate("date").toLocalDate()));
        }

        List<ConcertHall> concertHalls = concertHallDAO.getAll();
        List<Organizer> organizers = organizerDAO.getAll();

        for (ConcertInHall concertInHall : list){
            concertInHall.setConcertHall(concertHalls.stream()
                    .filter(el -> el.getId() == concertInHall.getConcertHallId())
                    .findFirst().orElse(null));
            concertInHall.setOrganizer(organizers.stream().filter(el -> el.getId() == concertInHall.getOrganizerId())
                    .findFirst().orElse(null));
        }

        return list;
    }
}

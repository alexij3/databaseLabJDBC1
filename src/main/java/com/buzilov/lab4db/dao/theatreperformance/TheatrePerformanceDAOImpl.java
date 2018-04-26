package com.buzilov.lab4db.dao.theatreperformance;

import com.buzilov.lab4db.dao.organizer.OrganizerDAOImpl;
import com.buzilov.lab4db.dao.theatre.TheatreDAOImpl;
import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Organizer;
import com.buzilov.lab4db.model.Theatre;
import com.buzilov.lab4db.model.TheatrePerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TheatrePerformanceDAOImpl implements TheatrePerformanceDAO {
    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Autowired
    OrganizerDAOImpl organizerDAO;

    @Autowired
    TheatreDAOImpl theatreDAO;

    Connection con;
    Statement statement;

    @Override
    public TheatrePerformance insert(TheatrePerformance theatrePerformance) throws SQLException {
        System.out.println("insert theatre " + theatrePerformance);
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertTheatrePerformance = "INSERT INTO theatre_performance (id_theatre, name, id_organizer, date) VALUES (?, ?, ?, ?)";
        insert = con.prepareStatement(insertTheatrePerformance);
        insert.setInt(1, theatrePerformance.getTheatreId());
        insert.setString(2, theatrePerformance.getName());
        insert.setInt(3, theatrePerformance.getOrganizerId()   );
        insert.setDate(4, java.sql.Date.valueOf(theatrePerformance.getDate()));
        insert.executeUpdate();

        con.close();
        return theatrePerformance;
    }

    @Override
    public TheatrePerformance get(int id) {
        return null;
    }

    @Override
    public TheatrePerformance update(TheatrePerformance theatre) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateConcertInHall = "UPDATE theatre_performance SET id_theatre = ?, name = ?, id_organizer = ?, date = ? where id_performance = ?";
        System.out.println(theatre);
        update = con.prepareStatement(updateConcertInHall);
        update.setInt(1, theatre.getTheatreId());
        update.setString(2, theatre.getName());
        update.setInt(3, theatre.getOrganizerId());
        update.setDate(4, java.sql.Date.valueOf(theatre.getDate()));
        update.setInt(5, theatre.getId());
        update.executeUpdate();

        con.close();
        return theatre;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteTheatrePerformance = "DELETE FROM theatre_performance WHERE id_performance = ?";
        delete = con.prepareStatement(deleteTheatrePerformance);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<TheatrePerformance> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List <TheatrePerformance> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM theatre_performance");

        while (rs.next()){
            list.add(new TheatrePerformance(rs.getInt("id_performance"),
                    rs.getInt("id_theatre"), rs.getString("name"), rs.getInt("id_organizer"), rs.getDate("date").toLocalDate()));
        }

        List<Theatre> theatres = theatreDAO.getAll();
        List<Organizer> organizers = organizerDAO.getAll();

        for (TheatrePerformance theatrePerformance : list){
            theatrePerformance.setTheatre(theatres.stream()
                    .filter(el -> el.getId() == theatrePerformance.getTheatreId())
                    .findFirst().orElse(null));
            theatrePerformance.setOrganizer(organizers.stream().filter(el -> el.getId() == theatrePerformance.getOrganizerId())
                    .findFirst().orElse(null));
        }

        return list;
    }

}

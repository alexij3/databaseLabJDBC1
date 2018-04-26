package com.buzilov.lab4db.dao.contestinpalace;

import com.buzilov.lab4db.dao.culturepalace.CulturePalaceDAOImpl;
import com.buzilov.lab4db.dao.organizer.OrganizerDAOImpl;
import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.ContestInPalace;
import com.buzilov.lab4db.model.CulturePalace;
import com.buzilov.lab4db.model.Organizer;
import com.buzilov.lab4db.service.culturepalace.CulturePalaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContestInPalaceDAOImpl implements ContestInPalaceDAO {
    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Autowired
    CulturePalaceDAOImpl culturePalaceDAO;

    @Autowired
    OrganizerDAOImpl organizerDAO;

    Connection con;
    Statement statement;

    @Override
    public ContestInPalace insert(ContestInPalace contestInPalace) throws SQLException {
        System.out.println("insert contest " + contestInPalace);
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertContestInPalace = "INSERT INTO contest_in_palace (id_palace, name, id_organizer, date) VALUES (?, ?, ?, ?)";
        insert = con.prepareStatement(insertContestInPalace);
        insert.setInt(1, contestInPalace.getCulturePalaceId());
        insert.setString(2, contestInPalace.getName());
        insert.setInt(3, contestInPalace.getOrganizerId()   );
        insert.setDate(4, java.sql.Date.valueOf(contestInPalace.getDate()));
        insert.executeUpdate();

        con.close();
        return contestInPalace;
    }

    @Override
    public ContestInPalace get(int id) {
        return null;
    }

    @Override
    public ContestInPalace update(ContestInPalace contest) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateConcertInHall = "UPDATE contest_in_palace SET id_palace = ?, name = ?, id_organizer = ?, date = ? where id_contest = ?";
        System.out.println(contest);
        update = con.prepareStatement(updateConcertInHall);
        update.setInt(1, contest.getCulturePalaceId());
        update.setString(2, contest.getName());
        update.setInt(3, contest.getOrganizerId());
        update.setDate(4, java.sql.Date.valueOf(contest.getDate()));
        update.setInt(5, contest.getId());
        update.executeUpdate();

        con.close();
        return contest;
    }

    @Override
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteContestInPalace = "DELETE FROM contest_in_palace WHERE id_contest = ?";
        delete = con.prepareStatement(deleteContestInPalace);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<ContestInPalace> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List <ContestInPalace> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT *" +
                                                " FROM contest_in_palace");

        while (rs.next()){
            list.add(new ContestInPalace(rs.getInt("id_contest"),
                    rs.getInt("id_palace"), rs.getString("name"), rs.getInt("id_organizer"),
                    rs.getDate("date").toLocalDate()));
        }

        List<CulturePalace> culturePalaces = culturePalaceDAO.getAll();
        List<Organizer> organizers = organizerDAO.getAll();

        for (ContestInPalace contestInPalace : list){
            contestInPalace.setCulturePalace(culturePalaces.stream()
                    .filter(el -> el.getId() == contestInPalace.getCulturePalaceId())
                    .findFirst().orElse(null));
            contestInPalace.setOrganizer(organizers.stream().filter(el -> el.getId() == contestInPalace.getOrganizerId())
                                        .findFirst().orElse(null));
        }

        return list;
    }
}

package com.buzilov.lab4db.dao.contestresults;

import com.buzilov.lab4db.dao.artist.ArtistDAOImpl;
import com.buzilov.lab4db.dao.contestinpalace.ContestInPalaceDAOImpl;
import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.ContestInPalace;
import com.buzilov.lab4db.model.ContestResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContestResultsDAOImpl implements ContestResultsDAO {
    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Autowired
    ContestInPalaceDAOImpl contestInPalaceDAO;

    @Autowired
    ArtistDAOImpl artistDAO;

    Connection con;
    Statement statement;

    @Override
    public ContestResults insert(ContestResults contestResult) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertContestResult = "INSERT INTO contest_results (id_contest, id_artist, place, is_winner) VALUES (?, ?, ?, ?)";
        insert = con.prepareStatement(insertContestResult);
        insert.setInt(1, contestResult.getContestId());
        insert.setInt(2, contestResult.getArtistId());
        insert.setInt(3, contestResult.getPlace());
        insert.setString(4, String.valueOf(contestResult.getIsWinner()));
        insert.executeUpdate();

        con.close();
        return contestResult;
    }

    @Override
    public ContestResults get(int id) {
        return null;
    }

    @Override
    public ContestResults update(int oldContestId, int oldArtistId, ContestResults contestResult) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updatecontestResult = "UPDATE contest_results SET id_contest = ?, id_artist = ?, place = ?, is_winner = ? " +
                                     " WHERE id_contest = ? AND id_artist = ?";
        System.out.println(contestResult);
        update = con.prepareStatement(updatecontestResult);
        update.setInt(1, contestResult.getContestId());
        update.setInt(2, contestResult.getArtistId());
        update.setInt(3, contestResult.getPlace());
        update.setString(4, String.valueOf(contestResult.getIsWinner()));
        update.setInt(5, oldContestId);
        update.setInt(6, oldArtistId);
        update.executeUpdate();

        con.close();
        return contestResult;
    }

    @Override
    public void delete(int contestId, int artistId) throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteContestResult = "DELETE FROM contest_results WHERE id_contest = ? AND id_artist = ?";
        delete = con.prepareStatement(deleteContestResult);
        delete.setInt(1, contestId);
        delete.setInt(2, artistId);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<ContestResults> getAll() throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List <ContestResults> list = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT * FROM contest_results");

        while (rs.next()){
            list.add(new ContestResults(rs.getInt("id_contest"), rs.getInt("id_artist"),
                    rs.getInt("place"), rs.getString("is_winner").charAt(0)));
        }

        List<Artist> artists = artistDAO.getAll();
        List<ContestInPalace> contestInPalaces = contestInPalaceDAO.getAll();

        for (ContestResults c : list){
            c.setArtist(artists.stream().filter(el->el.getId() == c.getArtistId()).findFirst().orElse(null));
            c.setContest(contestInPalaces.stream().filter(el->el.getId() == c.getContestId()).findFirst().orElse(null));
        }

        return list;
    }
}

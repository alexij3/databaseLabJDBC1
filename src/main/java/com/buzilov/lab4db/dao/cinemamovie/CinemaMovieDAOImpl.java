package com.buzilov.lab4db.dao.cinemamovie;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.Cinema;
import com.buzilov.lab4db.model.CinemaMovie;
import com.buzilov.lab4db.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CinemaMovieDAOImpl implements CinemaMovieDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    Connection con;
    Statement statement;

    @Override
    public CinemaMovie insertCinemaMovie(CinemaMovie cinemaMovie)  throws SQLException{
        System.out.println("insert cinema " + cinemaMovie);
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());

        PreparedStatement insert;
        String insertMovie = "INSERT INTO cinema_movie (id_movie, name, genre, id_cinema, date) VALUES (?, ?, ?, ?, ?)";
        insert = con.prepareStatement(insertMovie);
        insert.setInt(1, cinemaMovie.getId());
        insert.setString(2, cinemaMovie.getName());
        insert.setString(3, cinemaMovie.getGenre().toString());
        insert.setInt(4, cinemaMovie.getCinemaId());
        insert.setDate(5, java.sql.Date.valueOf(cinemaMovie.getDate()));
        insert.executeUpdate();

        con.close();
        return cinemaMovie;
    }

    @Override
    public CinemaMovie getCinemaMovie(int id) {
        return dataStorage.getCinemaMovies().stream()
                .filter(el -> el.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public CinemaMovie updateCinemaMovie(CinemaMovie cinemaMovie)  throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement update;
        String updateCinemaMovie = "UPDATE cinema_movie SET name = ?, genre = ?, id_cinema = ?, date = ? where id_movie = ?";
        System.out.println(cinemaMovie);
        update = con.prepareStatement(updateCinemaMovie);
        update.setString(1, cinemaMovie.getName());
        update.setString(2, cinemaMovie.getGenre().toString());
        update.setInt(3, cinemaMovie.getCinemaId());
        update.setDate(4, java.sql.Date.valueOf(cinemaMovie.getDate()));
        update.setInt(5, cinemaMovie.getId());
        update.executeUpdate();

        con.close();
        return cinemaMovie;
    }

    @Override
    public void deleteCinemaMovie(int id)  throws SQLException{
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        PreparedStatement delete;
        String deleteMovie = "DELETE FROM cinema_movie WHERE id_movie = ?";
        delete = con.prepareStatement(deleteMovie);
        delete.setInt(1, id);
        delete.executeUpdate();
        con.close();
    }

    @Override
    public List<CinemaMovie> getAll()  throws SQLException {
        con = DriverManager.getConnection(dataStorageJdbc.getUrl(), dataStorageJdbc.getLogin(), dataStorageJdbc.getPassword());
        statement = con.createStatement();

        List<CinemaMovie> list = new ArrayList<>();
        ResultSet rs = this.executeQuery("SELECT * FROM cinema_movie");

        while (rs.next()){
            list.add(new CinemaMovie(rs.getInt("id_movie"), rs.getString("name"),
                    Genre.valueOf(rs.getString("genre")), rs.getInt("id_cinema"), rs.getDate("date").toLocalDate()));
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

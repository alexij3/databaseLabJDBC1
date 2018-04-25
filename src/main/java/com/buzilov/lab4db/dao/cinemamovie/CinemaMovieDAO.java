package com.buzilov.lab4db.dao.cinemamovie;

import com.buzilov.lab4db.model.CinemaMovie;

import java.sql.SQLException;
import java.util.List;

public interface CinemaMovieDAO {
    CinemaMovie insertCinemaMovie(CinemaMovie cinemaMovie) throws SQLException;
    CinemaMovie getCinemaMovie(int id) throws SQLException;
    CinemaMovie updateCinemaMovie(CinemaMovie cinemaMovie) throws SQLException;
    void deleteCinemaMovie(int id) throws SQLException;
    List<CinemaMovie> getAll() throws SQLException;
}

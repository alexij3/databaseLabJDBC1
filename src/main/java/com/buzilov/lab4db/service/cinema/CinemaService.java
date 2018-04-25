package com.buzilov.lab4db.service.cinema;

import com.buzilov.lab4db.model.Cinema;

import java.sql.SQLException;
import java.util.List;

public interface CinemaService {
    Cinema insertCinema(Cinema cinema) throws SQLException;
    Cinema getCinema(int id) throws SQLException;
    Cinema updateCinema(Cinema cinema) throws SQLException;
    void deleteCinema(int id) throws SQLException;
    List<Cinema> getAll() throws SQLException;
}

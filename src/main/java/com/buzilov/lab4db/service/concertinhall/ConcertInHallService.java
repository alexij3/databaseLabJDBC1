package com.buzilov.lab4db.service.concertinhall;

import com.buzilov.lab4db.model.ConcertInHall;

import java.sql.SQLException;
import java.util.List;

public interface ConcertInHallService {
    ConcertInHall insert(ConcertInHall concertInHall)  throws SQLException;
    ConcertInHall get(int id) throws SQLException;
    ConcertInHall update(ConcertInHall concertInHall) throws SQLException;
    void delete(int id) throws SQLException;
    List<ConcertInHall> getAll() throws SQLException;
}

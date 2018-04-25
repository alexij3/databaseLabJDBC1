package com.buzilov.lab4db.service.theatre;

import com.buzilov.lab4db.model.Theatre;

import java.sql.SQLException;
import java.util.List;

public interface TheatreService {
    Theatre insert(Theatre theatre) throws SQLException;
    Theatre get(int id) throws SQLException;
    Theatre update(Theatre theatre) throws SQLException;
    void delete(int id) throws SQLException;
    List<Theatre> getAll() throws SQLException;
}

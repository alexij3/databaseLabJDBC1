package com.buzilov.lab4db.service.contestinpalace;

import com.buzilov.lab4db.model.ContestInPalace;

import java.sql.SQLException;
import java.util.List;

public interface ContestInPalaceService {
    ContestInPalace insert(ContestInPalace contest)  throws SQLException;
    ContestInPalace get(int id) throws SQLException;
    ContestInPalace update(ContestInPalace contest) throws SQLException;
    void delete(int id) throws SQLException;
    List<ContestInPalace> getAll() throws SQLException;
}

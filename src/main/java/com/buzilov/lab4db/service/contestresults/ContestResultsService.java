package com.buzilov.lab4db.service.contestresults;

import com.buzilov.lab4db.model.ContestResults;

import java.sql.SQLException;
import java.util.List;

public interface ContestResultsService {
    ContestResults insert(ContestResults contest) throws SQLException;
    ContestResults get(int id) throws SQLException;
    ContestResults update(ContestResults contest) throws SQLException;
    void delete(int id) throws SQLException;
    List<ContestResults> getAll() throws SQLException;
}

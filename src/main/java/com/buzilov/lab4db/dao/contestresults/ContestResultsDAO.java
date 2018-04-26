package com.buzilov.lab4db.dao.contestresults;

import com.buzilov.lab4db.model.ContestResults;

import java.sql.SQLException;
import java.util.List;

public interface ContestResultsDAO {
    ContestResults insert(ContestResults contest) throws SQLException;
    ContestResults get(int id);
    ContestResults update(ContestResults contest);
    void delete(int id);
    List<ContestResults> getAll() throws SQLException;
}

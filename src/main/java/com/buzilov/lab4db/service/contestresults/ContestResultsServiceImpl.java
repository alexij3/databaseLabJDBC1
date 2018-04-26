package com.buzilov.lab4db.service.contestresults;

import com.buzilov.lab4db.dao.contestresults.ContestResultsDAOImpl;
import com.buzilov.lab4db.model.ContestResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContestResultsServiceImpl implements ContestResultsService {
    @Autowired
    ContestResultsDAOImpl contestResultsDAO;

    @Override
    public ContestResults insert(ContestResults contest) throws SQLException {
        return contestResultsDAO.insert(contest);
    }

    @Override
    public ContestResults get(int id) throws SQLException {
        return contestResultsDAO.get(id);
    }

    @Override
    public ContestResults update(ContestResults contest) throws SQLException {
        return contestResultsDAO.update(contest);
    }

    @Override
    public void delete(int id) throws SQLException {
        contestResultsDAO.delete(id);
    }

    @Override
    public List<ContestResults> getAll() throws SQLException {
        return contestResultsDAO.getAll();
    }
}

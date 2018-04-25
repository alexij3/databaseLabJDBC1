package com.buzilov.lab4db.service.contestresults;

import com.buzilov.lab4db.dao.contestresults.ContestResultsDAOImpl;
import com.buzilov.lab4db.model.ContestResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestResultsServiceImpl implements ContestResultsService {
    @Autowired
    ContestResultsDAOImpl contestResultsDAO;

    @Override
    public ContestResults insertContestResults(ContestResults contest) {
        return contestResultsDAO.insertContestResults(contest);
    }

    @Override
    public ContestResults getContestResults(int id) {
        return contestResultsDAO.getContestResults(id);
    }

    @Override
    public ContestResults updateContestResults(ContestResults contest) {
        return contestResultsDAO.updateContestResults(contest);
    }

    @Override
    public ContestResults deleteContestResults(int id) {
        return contestResultsDAO.deleteContestResults(id);
    }

    @Override
    public List<ContestResults> getAll() {
        return contestResultsDAO.getAll();
    }
}

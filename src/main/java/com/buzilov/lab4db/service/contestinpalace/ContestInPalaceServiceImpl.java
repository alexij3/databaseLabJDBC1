package com.buzilov.lab4db.service.contestinpalace;

import com.buzilov.lab4db.dao.contestinpalace.ContestInPalaceDAO;
import com.buzilov.lab4db.model.ContestInPalace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestInPalaceServiceImpl implements ContestInPalaceService {
    @Autowired
    ContestInPalaceDAO contestInPalaceDAO;

    @Override
    public ContestInPalace insertContestInPalace(ContestInPalace contest) {
        return contestInPalaceDAO.insertContestInPalace(contest);
    }

    @Override
    public ContestInPalace getContestInPalace(int id) {
        return contestInPalaceDAO.getContestInPalace(id);
    }

    @Override
    public ContestInPalace updateContestInPalace(ContestInPalace contest) {
        return contestInPalaceDAO.updateContestInPalace(contest);
    }

    @Override
    public ContestInPalace deleteContestInPalace(int id) {
        return contestInPalaceDAO.deleteContestInPalace(id);
    }

    @Override
    public List<ContestInPalace> getAll() {
        return contestInPalaceDAO.getAll();
    }
}

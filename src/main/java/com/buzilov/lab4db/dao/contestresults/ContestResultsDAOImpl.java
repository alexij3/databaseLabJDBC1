package com.buzilov.lab4db.dao.contestresults;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.ContestResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContestResultsDAOImpl implements ContestResultsDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Override
    public ContestResults insertContestResults(ContestResults contestResults ) {
        dataStorage.getContestResults().add(contestResults);
        return contestResults;
    }

    @Override
    public ContestResults getContestResults(int id) {
        return dataStorage.getContestResults().stream()
                .filter(el -> el.getContest().getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public ContestResults updateContestResults(ContestResults contestResults ) {
        for(ContestResults cm: dataStorage.getContestResults())
        {
            if(cm.getContest().getId() == contestResults.getContest().getId())
            {
                cm.setContest(contestResults.getContest());
                cm.setArtist(contestResults.getArtist());
                cm.setPlace(contestResults.getPlace());
                cm.setIsWinner(contestResults.getIsWinner());
                break;
            }
        }
        return contestResults;
    }

    @Override
    public ContestResults deleteContestResults(int id) {
        ContestResults contestResults  = dataStorage.getContestResults()
                .stream()
                .filter(el -> el.getContest().getId() == id)
                .findFirst()
                .get();
        dataStorage.getArtists().remove(contestResults);
        return contestResults;
    }

    @Override
    public List<ContestResults> getAll() {
        return dataStorage.getContestResults();
    }
}

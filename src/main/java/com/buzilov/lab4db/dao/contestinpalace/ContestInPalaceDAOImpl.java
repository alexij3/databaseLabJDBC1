package com.buzilov.lab4db.dao.contestinpalace;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.ContestInPalace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContestInPalaceDAOImpl implements ContestInPalaceDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Override
    public ContestInPalace insertContestInPalace(ContestInPalace contestInPalace ) {
        dataStorage.getContestsInPalaces().add(contestInPalace);
        return contestInPalace;
    }

    @Override
    public ContestInPalace getContestInPalace(int id) {
        return dataStorage.getContestsInPalaces().stream()
                .filter(el -> el.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public ContestInPalace updateContestInPalace(ContestInPalace contestInPalace ) {
        for(ContestInPalace cm: dataStorage.getContestsInPalaces())
        {
            if(cm.getId() == contestInPalace.getId())
            {
                cm.setName(contestInPalace.getName());
                cm.setCulturePalace(contestInPalace.getCulturePalace());
                cm.setDate(contestInPalace.getDate());
                cm.setOrganizer(contestInPalace.getOrganizer());
                break;
            }
        }
        return contestInPalace;
    }

    @Override
    public ContestInPalace deleteContestInPalace(int id) {
        ContestInPalace contestInPalace  = dataStorage.getContestsInPalaces()
                .stream()
                .filter(el -> el.getId() == id)
                .findFirst()
                .get();
        dataStorage.getArtists().remove(contestInPalace);
        return contestInPalace;
    }

    @Override
    public List<ContestInPalace> getAll() {
        return dataStorage.getContestsInPalaces();
    }
}

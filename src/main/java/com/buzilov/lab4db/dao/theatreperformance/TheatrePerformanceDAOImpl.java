package com.buzilov.lab4db.dao.theatreperformance;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.datastorage.DataStorageJdbc;
import com.buzilov.lab4db.model.TheatrePerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TheatrePerformanceDAOImpl implements TheatrePerformanceDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Autowired
    DataStorageJdbc dataStorageJdbc;

    @Override
    public TheatrePerformance insertTheatrePerformance(TheatrePerformance theatrePerformance) {
        dataStorage.getTheatrePerformances().add(theatrePerformance);
        return theatrePerformance;
    }

    @Override
    public TheatrePerformance getTheatrePerformance(int id) {
        return dataStorage.getTheatrePerformances().stream()
                .filter(el -> el.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public TheatrePerformance updateTheatrePerformance(TheatrePerformance theatrePerformance) {
        for(TheatrePerformance cm: dataStorage.getTheatrePerformances())
        {
            if(cm.getId() == theatrePerformance.getId())
            {
                cm.setName(theatrePerformance.getName());
                cm.setTheatre(theatrePerformance.getTheatre());
                cm.setOrganizer(theatrePerformance.getOrganizer());
                cm.setDate(theatrePerformance.getDate());
                break;
            }
        }
        return theatrePerformance;
    }

    @Override
    public TheatrePerformance deleteTheatrePerformance(int id) {
        TheatrePerformance theatrePerformance = dataStorage.getTheatrePerformances()
                .stream()
                .filter(el -> el.getId() == id)
                .findFirst()
                .get();
        dataStorage.getArtists().remove(theatrePerformance);
        return theatrePerformance;
    }

    @Override
    public List<TheatrePerformance> getAll() {
        return dataStorage.getTheatrePerformances();
    }
}

package com.buzilov.lab4db.service.theatreperformance;

import com.buzilov.lab4db.dao.theatreperformance.TheatrePerformanceDAOImpl;
import com.buzilov.lab4db.model.TheatrePerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatrePerformanceServiceImpl implements TheatrePerformanceService {
    @Autowired
    TheatrePerformanceDAOImpl theatrePerformanceDAO;

    @Override
    public TheatrePerformance insertTheatrePerformance(TheatrePerformance theatrePerformance) {
        return theatrePerformanceDAO.insertTheatrePerformance(theatrePerformance);
    }

    @Override
    public TheatrePerformance getTheatrePerformance(int id) {
        return theatrePerformanceDAO.getTheatrePerformance(id);
    }

    @Override
    public TheatrePerformance updateTheatrePerformance(TheatrePerformance theatrePerformance) {
        return theatrePerformanceDAO.updateTheatrePerformance(theatrePerformance);
    }

    @Override
    public TheatrePerformance deleteTheatrePerformance(int id) {
        return theatrePerformanceDAO.deleteTheatrePerformance(id);
    }

    @Override
    public List<TheatrePerformance> getAll() {
        return theatrePerformanceDAO.getAll();
    }
}

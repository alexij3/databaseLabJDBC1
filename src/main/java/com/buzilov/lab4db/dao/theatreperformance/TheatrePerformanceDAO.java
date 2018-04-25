package com.buzilov.lab4db.dao.theatreperformance;

import com.buzilov.lab4db.model.TheatrePerformance;

import java.util.List;

public interface TheatrePerformanceDAO {
    TheatrePerformance insertTheatrePerformance(TheatrePerformance theatrePerformance);
    TheatrePerformance getTheatrePerformance(int id);
    TheatrePerformance updateTheatrePerformance(TheatrePerformance theatrePerformance);
    TheatrePerformance deleteTheatrePerformance(int id);
    List<TheatrePerformance> getAll();
}

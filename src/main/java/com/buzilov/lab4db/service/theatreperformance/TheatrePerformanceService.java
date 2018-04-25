package com.buzilov.lab4db.service.theatreperformance;

import com.buzilov.lab4db.model.TheatrePerformance;

import java.util.List;

public interface TheatrePerformanceService {
    TheatrePerformance insertTheatrePerformance(TheatrePerformance theatrePerformance);
    TheatrePerformance getTheatrePerformance(int id);
    TheatrePerformance updateTheatrePerformance(TheatrePerformance theatrePerformance);
    TheatrePerformance deleteTheatrePerformance(int id);
    List<TheatrePerformance> getAll();
}

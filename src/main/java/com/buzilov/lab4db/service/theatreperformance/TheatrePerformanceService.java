package com.buzilov.lab4db.service.theatreperformance;

import com.buzilov.lab4db.model.TheatrePerformance;

import java.sql.SQLException;
import java.util.List;

public interface TheatrePerformanceService {
    TheatrePerformance insert(TheatrePerformance theatrePerformance)  throws SQLException;
    TheatrePerformance get(int id) throws SQLException;
    TheatrePerformance update(TheatrePerformance theatrePerformance) throws SQLException;
    void delete(int id) throws SQLException;
    List<TheatrePerformance> getAll() throws SQLException;
}

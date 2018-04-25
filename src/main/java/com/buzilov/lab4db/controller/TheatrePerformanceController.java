package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.TheatrePerformance;
import com.buzilov.lab4db.service.theatreperformance.TheatrePerformanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TheatrePerformanceController {
    @Autowired
    TheatrePerformanceServiceImpl theatrePerformanceService;

    @RequestMapping("/theatreperformance")
    public List<TheatrePerformance> showTheatrePerformances(){
        return theatrePerformanceService.getAll();
    }

}

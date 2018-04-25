package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.ConcertInHall;
import com.buzilov.lab4db.service.concerthall.ConcertHallServiceImpl;
import com.buzilov.lab4db.service.concertinhall.ConcertInHallServiceImpl;
import com.buzilov.lab4db.service.organizer.OrganizerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/concertinhall")
public class ConcertInHallController {
    @Autowired
    ConcertInHallServiceImpl concertInHallService;

    @Autowired
    ConcertHallServiceImpl concertHallService;

    @Autowired
    OrganizerServiceImpl organizerService;

    @RequestMapping("/showall")
    public List<ConcertInHall> show() throws SQLException{
        return concertInHallService.getAll();
    }

    @RequestMapping("/insert")
    public ConcertInHall insert(@RequestBody ConcertInHall concertInHall) throws SQLException{
        concertInHall.setConcertHall(concertHallService.get(concertInHall.getConcertHallId()));
        concertInHall.setOrganizer(organizerService.get(concertInHall.getOrganizerId()));
        return concertInHallService.insert(concertInHall);
    }
}

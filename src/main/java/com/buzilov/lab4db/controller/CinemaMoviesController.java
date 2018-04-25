package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.Cinema;
import com.buzilov.lab4db.model.CinemaMovie;
import com.buzilov.lab4db.service.cinema.CinemaServiceImpl;
import com.buzilov.lab4db.service.cinemamovie.CinemaMovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cinemamovie")
public class CinemaMoviesController {
    @Autowired
    CinemaMovieServiceImpl service;

    @Autowired
    CinemaServiceImpl cinemaService;

    @RequestMapping("/showAll")
    public List<CinemaMovie> getAll() throws SQLException {
        List<CinemaMovie> list = service.getAll();
        for (int i = 0; i < list.size(); i++){
            list.get(i).setCinema(cinemaService.getCinema(list.get(i).getCinemaId()));
        }
        return list;
    }

    @RequestMapping("/delete")
    public void delete(int id) throws SQLException {
        service.delete(id);
    }

    @RequestMapping("/create")
    public CinemaMovie create(@RequestBody CinemaMovie CinemaMovie) throws SQLException{
        CinemaMovie.setCinema(cinemaService.getCinema(CinemaMovie.getCinemaId()));
        if (CinemaMovie.getDate() == null){
            CinemaMovie.setDate(LocalDate.of(2018, 1, 1));
        }
        return service.insert(CinemaMovie);
    }

    @RequestMapping("/update")
    public CinemaMovie update(@RequestParam("id") Integer id, @RequestBody CinemaMovie CinemaMovie) throws SQLException{
        System.out.println("updfate");
        CinemaMovie.setId(id);
        CinemaMovie.setCinema(cinemaService.getCinema(CinemaMovie.getCinemaId()));
        if (CinemaMovie.getDate() == null){
            CinemaMovie.setDate(LocalDate.of(2018, 1, 1));
        }
        return service.update(CinemaMovie);
    }

}

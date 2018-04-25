package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.ContestResults;
import com.buzilov.lab4db.service.contestresults.ContestResultsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContestResultsController {
    @Autowired
    ContestResultsServiceImpl contestResultsService;

    @RequestMapping("/contestresults")
    public List<ContestResults> showContestResults(){
        return contestResultsService.getAll();
    }

}

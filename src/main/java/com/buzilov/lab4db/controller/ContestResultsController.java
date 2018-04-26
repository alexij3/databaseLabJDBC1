package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.ContestResults;
import com.buzilov.lab4db.service.contestresults.ContestResultsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/contestresults")
public class ContestResultsController {
    @Autowired
    ContestResultsServiceImpl contestResultsService;

    @RequestMapping("/showall")
    public List<ContestResults> showContestResults() throws SQLException {
        return contestResultsService.getAll();
    }

    @RequestMapping("/insert")
    public ContestResults insert(@RequestBody ContestResults contestResults) throws SQLException {
        return contestResultsService.insert(contestResults);
    }

}

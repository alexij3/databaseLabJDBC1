package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.ContestInPalace;
import com.buzilov.lab4db.service.contestinpalace.ContestInPalaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContestInPalaceController {
    @Autowired
    ContestInPalaceServiceImpl contestInPalaceService;

    @RequestMapping("/contestinpalace")
    public List<ContestInPalace> showContestsInPalaces(){
        return contestInPalaceService.getAll();
    }
}

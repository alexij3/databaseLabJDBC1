package com.buzilov.lab4db.service.contestinpalace;

import com.buzilov.lab4db.model.ContestInPalace;

import java.util.List;

public interface ContestInPalaceService {
    ContestInPalace insertContestInPalace(ContestInPalace contest);
    ContestInPalace getContestInPalace(int id);
    ContestInPalace updateContestInPalace(ContestInPalace contest);
    ContestInPalace deleteContestInPalace(int id);
    List<ContestInPalace> getAll();
}

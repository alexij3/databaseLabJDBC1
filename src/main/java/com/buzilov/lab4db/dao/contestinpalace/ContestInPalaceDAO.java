package com.buzilov.lab4db.dao.contestinpalace;

import com.buzilov.lab4db.model.ContestInPalace;

import java.util.List;

public interface ContestInPalaceDAO {
    ContestInPalace insertContestInPalace(ContestInPalace contest);
    ContestInPalace getContestInPalace(int id);
    ContestInPalace updateContestInPalace(ContestInPalace contest);
    ContestInPalace deleteContestInPalace(int id);
    List<ContestInPalace> getAll();
}

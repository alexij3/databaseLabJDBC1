package com.buzilov.lab4db.dao.organizer;

import com.buzilov.lab4db.model.Organizer;

import java.sql.SQLException;
import java.util.List;

public interface OrganizerDAO {
    Organizer insert(Organizer organizer) throws SQLException;
    Organizer get(int id) throws SQLException;
    Organizer update(Organizer organizer) throws SQLException;
    void delete(int id) throws SQLException;
    List<Organizer> getAll() throws SQLException;
}

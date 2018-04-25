package com.buzilov.lab4db.dao.culturepalace;

import com.buzilov.lab4db.model.CulturePalace;

import java.sql.SQLException;
import java.util.List;

public interface CulturePalaceDAO {
    CulturePalace insert(CulturePalace culturePalace) throws SQLException;
    CulturePalace get(int id) throws SQLException;
    CulturePalace update(CulturePalace culturePalace) throws SQLException;
    void delete(int id) throws SQLException;
    List<CulturePalace> getAll() throws SQLException;
}

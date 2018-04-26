package com.buzilov.lab4db.dao.impresariogenre;

import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.model.ImpresarioGenre;

import java.sql.SQLException;
import java.util.List;

public interface ImpresarioGenreDAO {
    ImpresarioGenre get(int id) throws SQLException;
    ImpresarioGenre update(String oldGenre, ImpresarioGenre impresarioGenre) throws SQLException;
    void delete(int id, Genre genre) throws SQLException;
    ImpresarioGenre insert(ImpresarioGenre impresarioGenre) throws SQLException;
    List<ImpresarioGenre> getAll() throws SQLException;
}

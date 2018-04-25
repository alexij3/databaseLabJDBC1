package com.buzilov.lab4db.service.impresariogenre;

import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.model.ImpresarioGenre;

import java.sql.SQLException;
import java.util.List;

public interface ImpresarioGenreService {
    ImpresarioGenre get(int id) throws SQLException;
    ImpresarioGenre update(ImpresarioGenre impresarioGenre) throws SQLException;
    void delete(int id, Genre genre) throws SQLException;
    ImpresarioGenre insert(ImpresarioGenre impresarioGenre) throws SQLException;
    List<ImpresarioGenre> getAll() throws SQLException;
}

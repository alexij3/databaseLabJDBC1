package com.buzilov.lab4db.dao.genre;

import com.buzilov.lab4db.model.Genre;

import java.util.List;

public interface GenreDAO {
    Genre getGenre(Genre genre);
    List<Genre> getAll();
}

package com.buzilov.lab4db.dao.genre;

import com.buzilov.lab4db.datastorage.DataStorageFake;
import com.buzilov.lab4db.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreDAOImpl implements GenreDAO {
    @Autowired
    DataStorageFake dataStorage;

    @Override
    public Genre getGenre(Genre genre) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }
}

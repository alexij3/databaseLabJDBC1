package com.buzilov.lab4db.service.artistgenre;

import com.buzilov.lab4db.dao.artistgenre.ArtistGenreDAOImpl;
import com.buzilov.lab4db.model.ArtistGenre;
import com.buzilov.lab4db.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ArtistGenreServiceImpl implements ArtistGenreService {
    @Autowired
    ArtistGenreDAOImpl artistGenreDAO;

    @Override
    public ArtistGenre get(int id) throws SQLException {
        return artistGenreDAO.get(id);
    }

    @Override
    public ArtistGenre update(ArtistGenre artistGenre) throws SQLException {
        return artistGenreDAO.update(artistGenre);
    }

    @Override
    public void delete(int id, Genre genre) throws SQLException {
        artistGenreDAO.delete(id, genre);
    }

    @Override
    public ArtistGenre insert(ArtistGenre artistGenre) throws SQLException {
        return artistGenreDAO.insert(artistGenre);
    }

    @Override
    public List<ArtistGenre> getAll() throws SQLException {
        return artistGenreDAO.getAll();
    }
}

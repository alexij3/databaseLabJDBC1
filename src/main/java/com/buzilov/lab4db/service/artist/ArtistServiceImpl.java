package com.buzilov.lab4db.service.artist;

import com.buzilov.lab4db.dao.artist.ArtistDAOImpl;
import com.buzilov.lab4db.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistDAOImpl artistDAO;

    @Override
    public Artist insertArtist(Artist artist)throws SQLException {
        return artistDAO.insertArtist(artist);
    }

    @Override
    public Artist getArtist(int id)throws SQLException {
        return artistDAO.getArtist(id);
    }

    @Override
    public Artist updateArtist(Artist artist)  throws  SQLException{
        return artistDAO.updateArtist(artist);
    }

    @Override
    public void deleteArtist(int id) throws SQLException{
        artistDAO.deleteArtist(id);
    }

    @Override
    public List<Artist> getAll() throws SQLException {
        return artistDAO.getAll();
    }
}

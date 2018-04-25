package com.buzilov.lab4db.service.impresariogenre;

import com.buzilov.lab4db.dao.impresariogenre.ImpresarioGenreDAOImpl;
import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.model.ImpresarioGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ImpresarioGenreServiceImpl implements ImpresarioGenreService{
    @Autowired
    ImpresarioGenreDAOImpl impresarioGenreDAO;

    @Override
    public ImpresarioGenre get(int id) throws SQLException {
        return impresarioGenreDAO.get(id);
    }

    @Override
    public ImpresarioGenre update(ImpresarioGenre impresarioGenre) throws SQLException {
        return impresarioGenreDAO.update(impresarioGenre);
    }

    @Override
    public void delete(int id, Genre genre) throws SQLException {
        impresarioGenreDAO.delete(id, genre);
    }

    @Override
    public ImpresarioGenre insert(ImpresarioGenre impresarioGenre) throws SQLException {
        return impresarioGenreDAO.insert(impresarioGenre);
    }

    @Override
    public List<ImpresarioGenre> getAll() throws SQLException {
        return impresarioGenreDAO.getAll();
    }
}

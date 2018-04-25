package com.buzilov.lab4db.dao.artistandimpresario;

import com.buzilov.lab4db.model.ArtistAndImpresario;

import java.sql.SQLException;
import java.util.List;

public interface ArtistAndImpresarioDAO {
    ArtistAndImpresario insert(ArtistAndImpresario artistAndImpresario)  throws SQLException;
    ArtistAndImpresario get(int id)  throws SQLException;
    ArtistAndImpresario update(ArtistAndImpresario artistAndImpresario)  throws SQLException;
    void delete(int id, int impresarioId)  throws SQLException;
    List<ArtistAndImpresario> getAll() throws SQLException;
}

package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.ArtistAndImpresario;
import com.buzilov.lab4db.model.ArtistGenre;
import com.buzilov.lab4db.service.artist.ArtistServiceImpl;
import com.buzilov.lab4db.service.artistandimpresario.ArtistAndImpresarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/artistimpresario")
public class ArtistAndImpresarioController {
    @Autowired
    ArtistAndImpresarioServiceImpl artistAndImpresarioService;

    @Autowired
    ArtistServiceImpl artistService;

    @RequestMapping("/showall")
    public List<ArtistAndImpresario> showArtistsAndImpresarios() throws SQLException{
        return artistAndImpresarioService.getAll();
    }

    @RequestMapping("/insert")
    public ArtistAndImpresario insert(@RequestBody ArtistAndImpresario artistAndImpresario) throws SQLException{
        System.out.println(artistAndImpresario);
        return artistAndImpresarioService.insert(artistAndImpresario);
    }

    @RequestMapping("/update")
    public ArtistAndImpresario update(@RequestParam("oldImpresarioId") int oldImpresarioId, @RequestBody ArtistAndImpresario artistAndImpresario) throws SQLException {
        System.out.println(oldImpresarioId);
        System.out.println(artistAndImpresario.getImpresarioId());
        return artistAndImpresarioService.update(oldImpresarioId, artistAndImpresario);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody ArtistAndImpresario artistAndImpresario) throws SQLException {
        artistAndImpresarioService.delete(artistAndImpresario.getArtistId(), artistAndImpresario.getImpresarioId());
    }
}

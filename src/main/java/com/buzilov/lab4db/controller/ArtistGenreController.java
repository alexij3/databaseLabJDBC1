package com.buzilov.lab4db.controller;

import com.buzilov.lab4db.model.Artist;
import com.buzilov.lab4db.model.ArtistGenre;
import com.buzilov.lab4db.model.Genre;
import com.buzilov.lab4db.service.artist.ArtistServiceImpl;
import com.buzilov.lab4db.service.artistgenre.ArtistGenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/artistgenre")
public class ArtistGenreController {
    @Autowired
    ArtistServiceImpl artistService;

    @Autowired
    ArtistGenreServiceImpl artistGenreService;

    @RequestMapping("/showgenres")
    public List<ArtistGenre> showGenres() throws SQLException {
        return artistGenreService.getAll();
    }

    @RequestMapping("/insertgenre")
    public ArtistGenre insertGenre(@RequestParam("id") int id, @RequestBody ArtistGenre artistGenre) throws SQLException{
        Artist artist = artistService.getArtist(id);
        artistGenre.setArtist(artist);
        System.out.println(artistGenre);
        return artistGenreService.insert(artistGenre);
    }

    @RequestMapping("/deletegenre")
    public void deleteGenre(@RequestParam("id") int id, @RequestBody ArtistGenre artistGenre) throws SQLException {
        artistGenreService.delete(id, artistGenre.getGenre());
    }

    @RequestMapping("/updategenre")
    public ArtistGenre updateGenre(@RequestParam("id") int id, @RequestBody ArtistGenre artistGenre) throws SQLException{
        Artist artist = artistService.getArtist(id);
        artistGenre.setArtist(artist);
        System.out.println(artistGenre);
        return artistGenreService.update(artistGenre);
    }
}

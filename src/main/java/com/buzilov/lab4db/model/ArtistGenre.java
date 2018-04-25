package com.buzilov.lab4db.model;

public class ArtistGenre {
    private Artist artist;
    private Genre genre;

    public ArtistGenre() {
    }

    public ArtistGenre(Artist artist) {
        this.artist = artist;
    }

    public ArtistGenre(Artist artist, Genre genre) {
        this.artist = artist;
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ArtistGenre{" +
                "artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}

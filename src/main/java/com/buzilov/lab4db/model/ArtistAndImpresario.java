package com.buzilov.lab4db.model;

public class ArtistAndImpresario {
    private Artist artist;
    private Impresario impresario;

    public ArtistAndImpresario() {
    }

    public ArtistAndImpresario(Artist artist, Impresario impresario) {
        this.artist = artist;
        this.impresario = impresario;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Impresario getImpresario() {
        return impresario;
    }

    public void setImpresario(Impresario impresario) {
        this.impresario = impresario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistAndImpresario that = (ArtistAndImpresario) o;

        if (artist != null ? !artist.equals(that.artist) : that.artist != null) return false;
        return impresario != null ? impresario.equals(that.impresario) : that.impresario == null;
    }

    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (impresario != null ? impresario.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArtistAndImpresario{" +
                "artist=" + artist +
                ", impresario=" + impresario +
                '}';
    }
}

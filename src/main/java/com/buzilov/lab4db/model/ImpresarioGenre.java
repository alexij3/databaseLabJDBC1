package com.buzilov.lab4db.model;

public class ImpresarioGenre {
    private Impresario impresario;
    private Genre genre;

    public ImpresarioGenre() {
    }

    public ImpresarioGenre(Impresario impresario) {
        this.impresario = impresario;
    }

    public ImpresarioGenre(Impresario impresario, Genre genre) {
        this.impresario = impresario;
        this.genre = genre;
    }

    public Impresario getImpresario() {
        return impresario;
    }

    public void setImpresario(Impresario impresario) {
        this.impresario = impresario;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ImpresarioGenre{" +
                "impresario=" + impresario +
                ", genre=" + genre +
                '}';
    }
}

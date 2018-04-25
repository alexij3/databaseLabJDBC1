package com.buzilov.lab4db.model;

import java.util.Date;

public class TheatrePerformance {
    private int id;
    private Theatre theatre;
    private String name;
    private Organizer organizer;
    private Date date;

    public TheatrePerformance(int id, String name, Theatre theatre, Organizer organizer, Date date) {
        this.id = id;
        this.theatre = theatre;
        this.name = name;
        this.organizer = organizer;
        this.date = date;
    }

    @Override
    public String toString() {
        return "TheatrePerformance{" +
                "id=" + id +
                ", theatre=" + theatre +
                ", name='" + name + '\'' +
                ", organizer=" + organizer +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

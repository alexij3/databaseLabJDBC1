package com.buzilov.lab4db.model;

import java.util.Date;

public class ContestInPalace {
    private int id;
    private CulturePalace culturePalace;
    private String name;
    private Organizer organizer;
    private Date date;

    public ContestInPalace(int id, CulturePalace palace, String name, Organizer organizer, Date date) {
        this.id = id;
        this.culturePalace = palace;
        this.name = name;
        this.organizer = organizer;
        this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CulturePalace getCulturePalace() {
        return culturePalace;
    }

    public void setCulturePalace(CulturePalace culturePalace) {
        this.culturePalace = culturePalace;
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

    @Override
    public String toString() {
        return "ContestInPalace{" +
                "id=" + id +
                ", culturePalace=" + culturePalace +
                ", name='" + name + '\'' +
                ", organizer=" + organizer +
                ", date=" + date +
                '}';
    }
}

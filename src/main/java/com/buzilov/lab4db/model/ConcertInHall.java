package com.buzilov.lab4db.model;

import java.time.LocalDate;
import java.util.Date;

public class ConcertInHall {
    private Integer id;
    private ConcertHall concertHall;
    private int concertHallId;
    private String name;
    private Organizer organizer;
    private int organizerId;
    private LocalDate date;

    public ConcertInHall() {
    }

    public ConcertInHall(Integer id, int concertHallId, String name, int organizerId, LocalDate date) {
        this.id = id;
        this.concertHallId = concertHallId;
        this.name = name;
        this.organizerId = organizerId;
        this.date = date;
    }

    public ConcertInHall(ConcertHall concertHall, int concertHallId, String name, Organizer organizer, int organizerId, LocalDate date) {
        this.concertHall = concertHall;
        this.concertHallId = concertHallId;
        this.name = name;
        this.organizer = organizer;
        this.organizerId = organizerId;
        this.date = date;
    }

    public ConcertInHall(Integer id, ConcertHall concertHall, int concertHallId, String name, Organizer organizer, int organizerId, LocalDate date) {
        this.id = id;
        this.concertHall = concertHall;
        this.concertHallId = concertHallId;
        this.name = name;
        this.organizer = organizer;
        this.organizerId = organizerId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ConcertInHall{" +
                "id=" + id +
                ", concertHall=" + concertHall +
                ", concertHallId=" + concertHallId +
                ", name='" + name + '\'' +
                ", organizer=" + organizer +
                ", organizerId=" + organizerId +
                ", date=" + date +
                '}';
    }

    public ConcertHall getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(ConcertHall concertHall) {
        this.concertHall = concertHall;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getConcertHallId() {
        return concertHallId;
    }

    public void setConcertHallId(int concertHallId) {
        this.concertHallId = concertHallId;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }


}

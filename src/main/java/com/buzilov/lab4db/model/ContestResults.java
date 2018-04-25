package com.buzilov.lab4db.model;

import java.util.HashMap;
import java.util.Map;

public class ContestResults {
    private ContestInPalace contest;
    private Artist artist;
    private int place;
    private char isWinner;


    public ContestResults(ContestInPalace contest, Artist artist, int place, char isWinner) {
        this.contest = contest;
        this.artist = artist;
        this.place = place;
        this.isWinner = isWinner;
    }

    public ContestInPalace getContest() {
        return contest;
    }

    public void setContest(ContestInPalace contest) {
        this.contest = contest;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public char getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(char isWinner) {
        this.isWinner = isWinner;
    }

    @Override
    public String toString() {
        return "ContestResults{" +
                "contest=" + contest +
                ", artist=" + artist +
                ", place=" + place +
                ", isWinner=" + isWinner +
                '}';
    }
}

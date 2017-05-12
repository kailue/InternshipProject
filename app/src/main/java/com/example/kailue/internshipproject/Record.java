package com.example.kailue.internshipproject;

/**
 * Created by KaiLue on 08-May-17.
 */

public class Record {
    private String station, date, status;

    public Record() {

    }

    public Record(String station, String date, String status) {
        this.station = station;
        this.date = date;
        this.status = status;
    }

    public String getStation() {
        return station;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

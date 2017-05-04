package com.example.kailue.internshipproject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KaiLue on 03-May-17.
 */

public class Auditor {
    private String name;
    private String email;
    private String department;
    private String lastAudDate;

    public Auditor() {

    }

    public Auditor(String name, String email, String department, String date) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.lastAudDate = date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getLastAudDate() {
        return lastAudDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLastAudDate(String date) {
        this.lastAudDate = date;
    }
}

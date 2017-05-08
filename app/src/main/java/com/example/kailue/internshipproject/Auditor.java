package com.example.kailue.internshipproject;


/**
 * Created by KaiLue on 03-May-17.
 */

public class Auditor {
    private String FirstName;
    private String LastName;
    private String Email;
    private String Dept;

    public Auditor() {

    }

    public Auditor(String fname, String lname, String email, String department) {
        this.FirstName = fname;
        this.LastName = lname;
        this.Email = email;
        this.Dept = department;
    }

    public String getFullName() {
        return FirstName + ", " + LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }



    public String getEmail() {
        return Email;
    }

    public String getDept() {
        return Dept;
    }

    public void setFirstName(String fname) {
        this.FirstName = fname;
    }

    public void setLastName(String lname) {
        this.LastName = lname;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setDept(String department) {
        this.Dept = department;
    }

}









///  Original
//public class Auditor {
//    private String name;
//    private String email;
//    private String department;
//    private String lastAudDate;
//
//    public Auditor() {
//
//    }
//
//    public Auditor(String name, String email, String department, String date) {
//        this.name = name;
//        this.email = email;
//        this.department = department;
//        this.lastAudDate = date;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public String getLastAudDate() {
//        return lastAudDate;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public void setLastAudDate(String date) {
//        this.lastAudDate = date;
//    }
//}
/////
package com.example.shivam.adminapplication;

public class Student {
    String enrollNo="";
    String name="";
    String dateOfBirth="";
    String course="";
    String branch="";
    String yearOfJoining="";
    String address="";

    public Student() {
    }

    public Student(String enrollNo, String name, String dateOfBirth, String course, String branch, String yearOfJoining, String address) {
        this.enrollNo = enrollNo;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.course = course;
        this.branch = branch;
        this.yearOfJoining = yearOfJoining;
        this.address = address;
    }

    public String getEnrollNo() {
        return enrollNo;
    }

    public void setEnrollNo(String enrollNo) {
        this.enrollNo = enrollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(String yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

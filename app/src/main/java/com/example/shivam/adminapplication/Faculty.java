package com.example.shivam.adminapplication;

public class Faculty {
    String name="";
    String teacherId="";
    String address="";

    public Faculty() {
    }

    public Faculty(String name, String teacherId, String address) {
        this.name = name;
        this.teacherId = teacherId;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

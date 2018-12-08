package com.example.shivam.adminapplication;

public class Subject {
    String subjectid="";
    String subjectname="";
    String course="";
    String branch="";
    String semester="";

    public Subject() {
    }

    public Subject(String subjectid, String subjectname, String course, String branch, String semester) {
        this.subjectid = subjectid;
        this.subjectname = subjectname;
        this.course = course;
        this.branch = branch;
        this.semester = semester;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

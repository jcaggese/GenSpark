package org.genspark.bootdemo2.Entity;

import java.util.List;

public class Course {

    private int courseid;
    private String title;
    private String instructor;

    private List<Student> students;

    public Course() {
    }

    public Course(int courseid, String title, String instructor, List<Student> students) {
        this.courseid = courseid;
        this.title = title;
        this.instructor = instructor;
        this.students = students;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

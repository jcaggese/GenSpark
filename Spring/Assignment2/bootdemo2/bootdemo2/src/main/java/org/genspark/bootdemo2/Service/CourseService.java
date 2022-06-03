package org.genspark.bootdemo2.Service;

import org.genspark.bootdemo2.Entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses();

    Course getCourse(int courseID);

    Course addCourse(Course course);

    Course updateCourse(Course course);

    String deleteCourse(int courseID);
}

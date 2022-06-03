package org.genspark.bootdemo2.Service;

import org.genspark.bootdemo2.Entity.Course;
import org.genspark.bootdemo2.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    List<Course> courses;

    public CourseServiceImpl() {
        courses = new ArrayList();
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("John", 1, 25));
        students.add(new Student("Bob", 2, 75));
        courses.add(new Course(101, "Spring Boot", "Dr. Doctor", students));
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public Course getCourse(int courseID) {
        for (Course course : courses) {
            if (course.getCourseid() == courseID)
                return course;
        }
        return null;
    }

    @Override
    public Course addCourse(Course course) {
        if(getCourse(course.getCourseid()) == null) {
            courses.add(course);
            return course;
        }
        return null;
    }

    @Override
    public Course updateCourse(Course course) {
        String title = course.getTitle();
        String instructor = course.getInstructor();
        List<Student> students = course.getStudents();
        Course thisCourse = getCourse(course.getCourseid());
        thisCourse.setTitle(title);
        thisCourse.setInstructor(instructor);
        thisCourse.setStudents(students);
        return thisCourse;
    }

    @Override
    public String deleteCourse(int courseID) {
        Course course = getCourse(courseID);
        courses.remove(course);
        return "Course deleted.";
    }
}

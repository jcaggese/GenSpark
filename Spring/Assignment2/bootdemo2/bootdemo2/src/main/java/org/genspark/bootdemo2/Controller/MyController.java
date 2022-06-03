package org.genspark.bootdemo2.Controller;

import org.genspark.bootdemo2.Entity.Course;
import org.genspark.bootdemo2.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private CourseService cServ;

    @GetMapping("/")
    public String home(){
        return "<html><h1>Welcome to the Homepage.</h1></html>";
    }

    @GetMapping("/greet")
    public String hello(@RequestParam(value="name", defaultValue="User") String name){
        return String.format("<html><h1>Hello, %s.</h1></html>", name);
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return cServ.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable(value="id") String id){
        return cServ.getCourse(Integer.parseInt(id));
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return cServ.addCourse(course);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course course) {
        return cServ.updateCourse(course);
    }

    @DeleteMapping("/courses/{id}")
    public String delCourse(@PathVariable String id) {
        return cServ.deleteCourse(Integer.parseInt(id));
    }
}

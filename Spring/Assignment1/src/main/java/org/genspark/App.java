package org.genspark;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

        Student student = (Student) context.getBean("Student");
        System.out.println("XML Based Dependency Injection: " + student);

        //Annotation configuration
        context = new ClassPathXmlApplicationContext("SpringAnnotation.xml");
        student = (Student) context.getBean("student");
        System.out.println("Annotation Based Dependency Injection: " + student);

        //Java Configuration
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        student = (Student) context.getBean(Student.class);
        System.out.println("Java Based Dependency Injection: " + student);
    }
}

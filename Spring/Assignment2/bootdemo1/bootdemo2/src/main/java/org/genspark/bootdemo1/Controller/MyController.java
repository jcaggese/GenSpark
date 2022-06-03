package org.genspark.bootdemo1.Controller;

import org.genspark.bootdemo1.Entity.Employee;
import org.genspark.bootdemo1.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private EmployeeService eServ;

    @GetMapping("/")
    public String home(){
        return "<html><h1>Employee Database.</h1></html>";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return eServ.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable(value="id") String id){
        return eServ.getEmployee(Integer.parseInt(id));
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        return eServ.addEmployee(emp);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee emp) {
        return eServ.updateEmployee(emp);
    }

    @DeleteMapping("/employees/{id}")
    public String delEmployee(@PathVariable String id) {
        return eServ.deleteEmployee(Integer.parseInt(id));
    }
}

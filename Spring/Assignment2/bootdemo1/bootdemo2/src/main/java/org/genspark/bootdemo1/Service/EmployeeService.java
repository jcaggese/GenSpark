package org.genspark.bootdemo1.Service;

import org.genspark.bootdemo1.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployee(int id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    String deleteEmployee(int id);
}

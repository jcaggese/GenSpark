package org.genspark.bootdemo1.Service;

import org.genspark.bootdemo1.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees;

    public EmployeeServiceImpl(){
        employees = new ArrayList<>();
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id)
                return employee;
        }
        return null;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if(getEmployee(employee.getId()) == null) {
            employees.add(employee);
            return employee;
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        String title = employee.getJobTitle();
        String access = employee.getSecurityAccess();
        String name = employee.getName();
        Employee thisEmp = getEmployee(employee.getId());
        thisEmp.setJobTitle(title);
        thisEmp.setSecurityAccess(access);
        thisEmp.setName(name);
        return thisEmp;
    }

    @Override
    public String deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        employees.remove(employee);
        return "Employee fired.";
    }
}

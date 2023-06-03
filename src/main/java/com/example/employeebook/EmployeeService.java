package com.example.employeebook;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int departamentId, int salary);

    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Employee salaryMinInDepartament(int departament);

    Employee salaryMaxInDepartament(int departament);

    String printEmployees();

    List<Employee> printEmployeesOfDep(int departamentId);
}


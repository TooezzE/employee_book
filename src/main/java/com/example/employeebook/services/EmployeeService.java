package com.example.employeebook.services;


import com.example.employeebook.Employee;
import com.example.employeebook.exceptions.BadRequestException;
import com.example.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.example.employeebook.exceptions.EmployeeNotFoundException;
import com.example.employeebook.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class EmployeeService {
    List<Employee> employees = new ArrayList<>();
    private int allowedEmployeesCount = 10;

    public Employee addEmployee(String firstName, String lastName, int salary, int departamentId){
        checkEmployeeData(firstName, lastName);
        if(employees.size() >= allowedEmployeesCount){
            throw new EmployeeStorageIsFullException("Нельзя добавить сотрудника. Коллекция переполнена");
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже есть в коллекции");
            }
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, departamentId);
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                employee = employees.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        } else {
            employees.remove(employee);
        }
        return employee;
    }


    public Employee findEmployee(String firstName, String lastName){
        Employee employee = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                employee = employees.get(i);
            }
        }
        if (employee == null){
            throw new EmployeeNotFoundException("Сотрудник не найден в коллекции");
        }
        return employee;
    }

        public Employee salaryMinInDepartament(int departament) {
        return employees.stream()
                .filter(e -> e.getDepartamentId() == departament)
                .min(Comparator.comparing(e -> e.getSalary()))
                .get();
    }

    public Employee salaryMaxInDepartament(int departament) {
        return employees.stream()
                .filter(e -> e.getDepartamentId() == departament)
                .max(Comparator.comparing(e -> e.getSalary()))
                .get();
    }
        public String printEmployees() {
        return employees.toString();
    }

    public List<Employee> printEmployeesOfDep(int departamentId){
        return employees.stream()
                .filter(e -> e.getDepartamentId() == departamentId)
                .collect(Collectors.toList());
    }


    public void checkEmployeeData(String firstName, String lastName){
        if(StringUtils.containsAny(firstName, " ", ",", " <([0-9{\\^-=$!|]})?*+.>")
                || StringUtils.containsAny(lastName, " ", ",", " <([0-9{\\^-=$!|]})?*+.>")) {
            throw new BadRequestException();
        } else if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
            throw new BadRequestException();
        }
    }

    public void changeEmployeesCountAllowed(int newCountAllowed){
        allowedEmployeesCount = newCountAllowed;
    }


}


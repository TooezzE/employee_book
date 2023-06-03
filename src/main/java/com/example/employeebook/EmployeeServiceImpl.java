package com.example.employeebook;


import com.example.employeebook.exceptions.BadRequestException;
import com.example.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.example.employeebook.exceptions.EmployeeNotFoundException;
import com.example.employeebook.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int departamentId){
        checkEmployeeData(firstName, lastName);
        if(employees.size() >= 10){
            throw new EmployeeStorageIsFullException("Нельзя добавить сотрудника. Коллекция переполнена");
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже есть в коллекции");
            }
        }
        StringUtils.capitalize(firstName);
        StringUtils.capitalize(lastName);
        Employee employee = new Employee(firstName, lastName, salary, departamentId);
        employees.add(employee);
        return employee;
    }

    @Override
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


    @Override
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

    @Override
    public Employee salaryMinInDepartament(int departament) {
        return employees.stream()
                .filter(e -> e.getDepartamentId() == departament)
                .min(Comparator.comparing(e -> e.getSalary()))
                .get();
    }

    @Override
    public Employee salaryMaxInDepartament(int departament) {
        return employees.stream()
                .filter(e -> e.getDepartamentId() == departament)
                .max(Comparator.comparing(e -> e.getSalary()))
                .get();
    }
    @Override
    public String printEmployees() {
        return employees.toString();
    }

    @Override
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


}


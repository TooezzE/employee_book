package com.example.employeebook.services;

import com.example.employeebook.Employee;
import com.example.employeebook.exceptions.DepartamentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartamentsService {

    private EmployeeService employeeService;
    private int departamentsCount = 5;

    public DepartamentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesInDep(int dep){
        if(dep == 0 || dep > departamentsCount){
            throw new DepartamentNotFoundException("Wrong departament number");
        }
        List<Employee> employeesOfOneDep = new ArrayList<>();
        for (Employee employee : employeeService.employees) {
            if(employee.getDepartamentId() == dep){
                employeesOfOneDep.add(employee);
            }
        }
        return employeesOfOneDep;
    }

    public int getSalarySumInDep(int dep){
        if(dep == 0 || dep > departamentsCount){
            throw new DepartamentNotFoundException("Wrong departament number");
        }
        int sum = 0;
        for (Employee employee : employeeService.employees) {
            if(employee.getDepartamentId() == dep) {
                sum = sum + employee.getSalary();
            }
        }
        return sum;
    }

    public int getMinSalaryInDep(int dep){
        if(dep == 0 || dep > departamentsCount){
            throw new DepartamentNotFoundException("Wrong departament number");
        }
        int min = Integer.MAX_VALUE;
        for (Employee employee : employeeService.employees) {
            if (employee.getDepartamentId() == dep){
                if(employee.getSalary() < min){
                    min = employee.getSalary();
                }
            }
        }
        return min;
    }

    public int getMaxSalaryInDep(int dep){
        if(dep == 0 || dep > departamentsCount){
            throw new DepartamentNotFoundException("Wrong departament number");
        }
        int max = Integer.MIN_VALUE;
        for (Employee employee : employeeService.employees) {
            if (employee.getDepartamentId() == dep){
                if(employee.getSalary() > max){
                    max = employee.getSalary();
                }
            }
        }
        return max;
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDeps(){
        Map<Integer, List<Employee>> employeesByDeps = new HashMap<>();
        List<Employee> employeesOfOneDep = new ArrayList<>();
        int currentDep = 1;
        while (currentDep <= departamentsCount) {
            for (Employee employee : employeeService.employees) {
                if (employee.getDepartamentId() == currentDep){
                    employeesOfOneDep.add(employee);
                }
            }
            employeesByDeps.put(currentDep, employeesOfOneDep);
            currentDep++;
        }
        return employeesByDeps;
    }

    private void addNewDepartament(){
        departamentsCount++;
    }
}

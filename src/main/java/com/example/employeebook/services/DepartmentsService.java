package com.example.employeebook.services;

import com.example.employeebook.dto.Employee;
import com.example.employeebook.exceptions.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentsService {

    private final EmployeeService employeeService;
    private static int currentDepartmentsCount = 5;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesInDep(int dep){
        if(dep == 0 || dep > currentDepartmentsCount){
            throw new DepartmentNotFoundException("Wrong department number");
        }
        List<Employee> employeesOfOneDep = new ArrayList<>();
        for (Employee employee : employeeService.getAll()) {
            if(employee.getDepartmentId() == dep){
                employeesOfOneDep.add(employee);
            }
        }
        return employeesOfOneDep;
    }

    public int getSalarySumInDep(int dep){
        if(dep == 0 || dep > currentDepartmentsCount){
            throw new DepartmentNotFoundException("Wrong department number");
        }
        int sum = 0;
        for (Employee employee : employeeService.getAll()) {
            if(employee.getDepartmentId() == dep) {
                sum = sum + employee.getSalary();
            }
        }
        return sum;
    }

    public int getSalarySumOfAllEmployees(){
        int sum = 0;
        for (Employee employee : employeeService.getAll()) {
            sum = sum + employee.getSalary();
        }
        return sum;
    }

    public int getMinSalaryInDep(int dep){
        if(dep == 0 || dep > currentDepartmentsCount){
            throw new DepartmentNotFoundException("Wrong department number");
        }
        int min = Integer.MAX_VALUE;
        for (Employee employee : employeeService.getAll()) {
            if (employee.getDepartmentId() == dep){
                if(employee.getSalary() < min){
                    min = employee.getSalary();
                }
            }
        }
        return min;
    }

    public int getMaxSalaryInDep(int dep){
        if(dep == 0 || dep > currentDepartmentsCount){
            throw new DepartmentNotFoundException("Wrong department number");
        }
        int max = 0;
        for (Employee employee : employeeService.getAll()) {
            if (employee.getDepartmentId() == dep){
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
        while (currentDep <= currentDepartmentsCount) {
            for (Employee employee : employeeService.getAll()) {
                if (employee.getDepartmentId() == currentDep){
                    employeesOfOneDep.add(employee);
                }
            }
            employeesByDeps.put(currentDep, employeesOfOneDep);
            currentDep++;
        }
        return employeesByDeps;
    }

    public static int getCurrentDepartmentsCount() {
        return currentDepartmentsCount;
    }

    private void createNewDepartment(){
        currentDepartmentsCount++;
    }
}

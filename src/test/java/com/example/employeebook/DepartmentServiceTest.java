package com.example.employeebook;

import com.example.employeebook.dto.Employee;
import com.example.employeebook.services.DepartmentsService;
import com.example.employeebook.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentsService departmentsService;

    @Test
    public void getEmployeesInDep(){
        Employee employee1 = new Employee("Egor", "Irt", 1000, 1);
        Employee employee2 = new Employee("Vlad", "Ten", 1000, 1);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 1000, 1);
        employeeService.addEmployee("Grisha", "Ops", 1000, 2);
        Mockito.when(departmentsService.getEmployeesInDep(1)).thenReturn(expected);
    }

    @Test
    public void salarySumInDep(){
        int expected = 2000;
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 1000, 1);
        Mockito.when(departmentsService.getSalarySumInDep(1)).thenReturn(expected);
    }

    @Test
    public void minSalaryInDep(){
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 10, 1);
        employeeService.addEmployee("Grisha", "Ops", 96, 1);
        int expected = 10;
        Mockito.when(departmentsService.getMinSalaryInDep(1)).thenReturn(expected);
    }

    @Test
    public void maxSalaryInDep(){
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 10, 1);
        employeeService.addEmployee("Grisha", "Ops", 96, 1);
        int expected = 1000;
        Mockito.when(departmentsService.getMaxSalaryInDep(1)).thenReturn(expected);
    }
}

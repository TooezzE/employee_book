package com.example.employeebook;

import com.example.employeebook.dto.Employee;
import com.example.employeebook.services.DepartmentsService;
import com.example.employeebook.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentsService departmentsService;
    List<Employee> employees = Arrays.asList(
          new Employee("Egor", "Irt", 500, 1),
            new Employee("Vlad", "Ten", 1500, 1),
        new Employee("Grisha", "Ops", 1000, 2)
    );

    @BeforeEach
    public void getAll(){
        Mockito.when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    public void getEmployeesInDep(){
        List<Employee> actual = departmentsService.getEmployeesInDep(1);
        List<Employee> expeted = Arrays.asList(
                new Employee("Egor", "Irt", 500, 1),
                new Employee("Vlad", "Ten", 1500, 1)
        );

        assertEquals(expeted, actual);
    }

    @Test
    public void salarySumInDep(){
        int expected = departmentsService.getSalarySumInDep(1);
        assertEquals(2000, expected);
    }

    @Test
    public void minSalaryInDep(){
        int expected = departmentsService.getMinSalaryInDep(1);
        assertEquals(500, expected);
    }

    @Test
    public void maxSalaryInDep(){
        int expected = departmentsService.getMaxSalaryInDep(1);
        assertEquals(1500, expected);
    }
}

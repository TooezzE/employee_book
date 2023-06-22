package com.example.employeebook;

import com.example.employeebook.dto.Employee;
import com.example.employeebook.services.DepartmentsService;
import com.example.employeebook.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentsService departmentsService;

    @Before
    public void setUp(){
        employeeService = new EmployeeService();
        departmentsService = new DepartmentsService(employeeService);
    }

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
        assertEquals(expected, departmentsService.getEmployeesInDep(1));
    }

    @Test
    public void salarySumInDep(){
        int expected = 2000;
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 1000, 1);
        assertEquals(expected, departmentsService.getSalarySumInDep(1));
    }

    @Test
    public void minSalaryInDep(){
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 10, 1);
        employeeService.addEmployee("Grisha", "Ops", 96, 1);
        int expected = 10;
        assertEquals(expected, departmentsService.getMinSalaryInDep(1));
    }

    @Test
    public void maxSalaryInDep(){
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.addEmployee("Vlad", "Ten", 10, 1);
        employeeService.addEmployee("Grisha", "Ops", 96, 1);
        int expected = 1000;
        assertEquals(expected, departmentsService.getMaxSalaryInDep(1));
    }
}

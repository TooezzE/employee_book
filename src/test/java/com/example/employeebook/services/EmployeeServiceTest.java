package com.example.employeebook.services;

import com.example.employeebook.dto.Employee;
import com.example.employeebook.exceptions.EmployeeAlreadyAddedException;
import com.example.employeebook.exceptions.EmployeeNotFoundException;
import com.example.employeebook.exceptions.EmployeeStorageIsFullException;
import com.example.employeebook.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Before
    public void setUp(){
        employeeService = new EmployeeService();
    }

    @Test
    public void throwsExceptionWhileAddingIfEmployeesStorageIsFull(){
        employeeService.changeEmployeesCountAllowed(1);
        employeeService.addEmployee("Egor", "Irt", 100, 1);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("New", "Extra", 90, 2));
    }

    @Test
    public void throwsExceptionIfEmployeeAlreadyAdded(){
        employeeService.addEmployee("Egor", "Irt", 100, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee("Egor", "Irt", 100, 1));
    }

    @Test
    public void throwsExceptionIfEmployeeNotFound(){
        employeeService.addEmployee("Vlad", "Ten", 1020, 2);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Egor", "Irt"));
        assertDoesNotThrow(() -> employeeService.findEmployee("Vlad", "Ten"));
    }

    @Test
    public void findingEmployeeReturnsCorrect(){
        Employee employee = new Employee("Egor", "Irt", 100, 1);
        Employee employee1 = new Employee("Vlad", "Ten", 1020, 2);
        employeeService.addEmployee("Egor", "Irt", 100, 1);
        employeeService.addEmployee("Vlad", "Ten", 1020, 2);

        assertEquals(employee, employeeService.findEmployee("Egor", "Irt"));
        assertEquals(employee1, employeeService.findEmployee("Vlad", "Ten"));
    }

    @Test
    public void removingEmployeeComesCorrect(){
        employeeService.addEmployee("Vlad", "Ten", 1020, 2);
        employeeService.addEmployee("Egor", "Irt", 1000, 1);
        employeeService.removeEmployee("Vlad", "Ten");
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Vlad", "Ten"));
    }



}

package in.kenz.service;

import in.kenz.entity.Employee;

import java.util.List;
import java.util.Scanner;

public interface EmployeeService {

    void addEmployee(Scanner sc);

    void findEmployeeById(Scanner sc);

    void findEmployeeByName(Scanner sc);

    void findAllEmployees();

    void updateEmployee(Scanner sc);

    void deleteEmployeeById(Scanner sc);
}

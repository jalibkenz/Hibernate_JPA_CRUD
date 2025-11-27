package in.kenz.service.impl;

import in.kenz.dao.impl.EmployeeDaoImpl;
import in.kenz.entity.Employee;
import in.kenz.exception.EmployeeNotFoundException;
import in.kenz.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

    @Override
    public void addEmployee(Scanner sc) {
        System.out.println("Enter the Employee Name");
        Employee employee = new Employee(sc.next());
        employeeDaoImpl.save(employee);
        System.out.println("✅ Employee saved!");
    }


    @Override
    public void findEmployeeById(Scanner sc) {
        while (true) {
            System.out.println("Enter the Employee Id (or type 'exit' to return):");

            String input = sc.next();  // read as string

            // Allow user to exit this method
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Returning to menu.");
                return;
            }

            try {
                // Validate format (digits only)
                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("Invalid ID format! Only numbers allowed.");
                }

                long employeeId = Long.parseLong(input);

                // Query database
                Employee employee = employeeDaoImpl.findById(employeeId);

                // Throw error if null
                if (employee == null) {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }

                // If success → print and exit loop
                System.out.println("Employee found:");
                System.out.println(employee);
                return; // success → exit method

            } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }


    @Override
    public void findEmployeeByName(Scanner sc) {
        while (true) {
            System.out.println("Enter the Employee Name (or type 'exit' to return):");

            String input = sc.next();

            // Allow exit
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Returning to menu.");
                return;
            }

            try {
                // Validate name pattern → only letters & spaces allowed
                if (!input.matches("^[A-Za-z ]+$")) {
                    throw new IllegalArgumentException("Invalid name format! Only alphabets and spaces allowed.");
                }

                // Find employee by name
                Employee employee = employeeDaoImpl.findByEmployeeName(input);

                // Not found
                if (employee == null) {
                    throw new EmployeeNotFoundException("Employee with name '" + input + "' not found.");
                }

                // Success
                System.out.println("Employee found:");
                System.out.println(employee);
                return;  // exit method

            } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }


    @Override
    public void findAllEmployees() {
        System.out.println(employeeDaoImpl.findAll());
    }


    @Override
    public void updateEmployee(Scanner sc) {
        while (true) {
            System.out.println("Enter the Employee Id for which we need to update details:");

            String input = sc.next();   // read raw input

            try {
                // Format Validation
                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("Invalid ID format! Only numbers allowed.");
                }

                long employeeId = Long.parseLong(input);

                // Data Validation
                Employee employeeToEdit = employeeDaoImpl.findById(employeeId);

                if (employeeToEdit == null) {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }

                sc.nextLine(); // consume newline after next()

                // 3️⃣ Update fields
                System.out.println("Enter the 🆕 Employee Name:");
                String newName = sc.nextLine();

                employeeToEdit.setEmployeeName(newName);
                employeeDaoImpl.save(employeeToEdit);

                System.out.println("✅ Employee updated successfully!");
                return;  // success → exit loop

            } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("Please try again.\n");
                // continue loop
            }
        }
    }


    @Override
    public void deleteEmployeeById(Scanner sc) {
        while (true) {
            System.out.println("Enter the Employee Id of whom the details have to be deleted (or type 'exit' to return):");

            String input = sc.next();

            // allow exit
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Returning to menu.");
                return;
            }

            try {
                // 1️⃣ Validate ID pattern
                if (!input.matches("\\d+")) {
                    throw new IllegalArgumentException("Invalid ID format! Only numbers are allowed.");
                }

                long employeeId = Long.parseLong(input);

                // 2️⃣ Check if employee exists before deleting
                Employee employee = employeeDaoImpl.findById(employeeId);
                if (employee == null) {
                    throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
                }

                // 3️⃣ Perform deletion
                employeeDaoImpl.deleteById(employeeId);
                System.out.println("✅ Employee with ID " + employeeId + " deleted successfully.");

                return; // delete success → exit method

            } catch (IllegalArgumentException | EmployeeNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }
    }


}

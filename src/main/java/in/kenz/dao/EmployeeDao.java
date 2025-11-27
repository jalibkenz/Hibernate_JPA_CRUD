package in.kenz.dao;

import in.kenz.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    Employee findById(Long employeeId);
    Employee findByEmployeeName(String employeeName);
    List<Employee> findAll();
    void deleteById(Long employeeId);
}

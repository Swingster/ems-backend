package learning.guide.ems_backend.service;

import learning.guide.ems_backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);

    List<EmployeeDto> getEmployeesByFirstName(String firstName);

    List<EmployeeDto> getEmployeesByLastName(String lastName);

}

package learning.guide.ems_backend.mapper;

import learning.guide.ems_backend.dto.EmployeeDto;
import learning.guide.ems_backend.entity.Employee;

public class EmployeeMapper {
    // Define mapping methods for Employee entity
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        if (employeeDto == null) {
            throw new IllegalArgumentException("Cannot map a null EmployeeDto");
        }
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}

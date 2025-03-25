package learning.guide.ems_backend.service.impl;

import learning.guide.ems_backend.dto.EmployeeDto;
import learning.guide.ems_backend.entity.Employee;
import learning.guide.ems_backend.exception.ResourceNotFoundException;
import learning.guide.ems_backend.mapper.EmployeeMapper;
import learning.guide.ems_backend.repository.EmployeeRepository;
import learning.guide.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().
                map((employee -> EmployeeMapper.mapToEmployeeDto(employee))).
                collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : " + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : " + employeeId));
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getEmployeesByFirstName(String firstName) {
        List<Employee> employees = employeeRepository.findByFirstName(firstName);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("Employee does not exist : " + firstName);
        }
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getEmployeesByLastName(String lastName) {
        List<Employee> employees = employeeRepository.findByLastName(lastName);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("Employee does not exist : " + lastName);
        }
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
}
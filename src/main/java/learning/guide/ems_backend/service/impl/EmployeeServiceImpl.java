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
}

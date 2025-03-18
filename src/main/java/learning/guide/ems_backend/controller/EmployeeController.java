package learning.guide.ems_backend.controller;

import learning.guide.ems_backend.dto.EmployeeDto;
import learning.guide.ems_backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Building an Employee REST API to get an Employee
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Building an Employee REST API to get All Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //Building an Employee REST API to update an Employee
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(updatedEmployeeDto);
    }

    //Building an Employee REST API to delete an Employee
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("The employee was successfully deleted");
    }

    //Building an Employee REST API to get an Employee by first name
    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByFirstName(@PathVariable("firstName") String firstName) {
        List<EmployeeDto> employees = employeeService.getEmployeesByFirstName(firstName);
        return ResponseEntity.ok(employees);
    }

    //Building an Employee REST API to get an Employee by last name
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByLastName(@PathVariable("lastName") String lastName) {
        List<EmployeeDto> employees = employeeService.getEmployeesByLastName(lastName);
        return ResponseEntity.ok(employees);
    }

}

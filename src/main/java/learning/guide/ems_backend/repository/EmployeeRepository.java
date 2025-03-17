package learning.guide.ems_backend.repository;

import learning.guide.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

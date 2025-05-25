package br.com.mottu.challange.repository;

import br.com.mottu.challange.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

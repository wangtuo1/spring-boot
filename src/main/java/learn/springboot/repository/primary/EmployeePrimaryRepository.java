package learn.springboot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Employee;

public interface EmployeePrimaryRepository extends JpaRepository<Employee, Long> {

}

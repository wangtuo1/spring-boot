package learn.springboot.repository.tertiary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Employee;

public interface EmployeeTertiaryRepository extends JpaRepository<Employee, Long> {

}

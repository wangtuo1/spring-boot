package learn.springboot.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Employee;

public interface EmployeeSecondaryRepository extends JpaRepository<Employee, Long> {

}

package learn.springboot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Department;

public interface DepartmentPrimaryRepository extends JpaRepository<Department, Long> {

}

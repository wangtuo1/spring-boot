package learn.springboot.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Department;

public interface DepartmentSecondaryRepository extends JpaRepository<Department, Long> {

}

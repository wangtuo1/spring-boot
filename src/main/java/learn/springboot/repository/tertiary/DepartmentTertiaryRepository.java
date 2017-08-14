package learn.springboot.repository.tertiary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.Department;

public interface DepartmentTertiaryRepository extends JpaRepository<Department, Long> {

}

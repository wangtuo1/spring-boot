package learn.springboot.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springboot.domain.Employee;

public interface EmployeeMongoRepository extends MongoRepository<Employee, Long> {

}

package learn.springboot.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springboot.domain.Department;

public interface DepartmentMongoRepository extends MongoRepository<Department, Long> {

}

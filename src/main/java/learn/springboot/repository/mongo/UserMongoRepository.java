package learn.springboot.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springboot.domain.User;

public interface UserMongoRepository extends MongoRepository<User, Long> {

}

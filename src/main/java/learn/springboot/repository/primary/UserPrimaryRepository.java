package learn.springboot.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.User;

public interface UserPrimaryRepository extends JpaRepository<User, Long> {

}

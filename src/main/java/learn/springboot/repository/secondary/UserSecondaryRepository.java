package learn.springboot.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.User;

public interface UserSecondaryRepository extends JpaRepository<User, Long> {

}

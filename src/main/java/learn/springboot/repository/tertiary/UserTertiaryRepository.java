package learn.springboot.repository.tertiary;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.springboot.domain.User;

public interface UserTertiaryRepository extends JpaRepository<User, Long> {

}

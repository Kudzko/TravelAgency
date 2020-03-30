package by.andersen.kudko.repository;

import by.andersen.kudko.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

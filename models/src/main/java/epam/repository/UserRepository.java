package epam.repository;

import epam.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String name);

    Long deleteByLogin(String name);

    boolean existsByLogin(String login);

}

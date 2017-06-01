package epam.repository;

import epam.beans.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByOrganization_Name(String name);

    List<Feedback> findByAuthor_Login(String name);

    Long deleteByAuthor_Login(String name);

    Long deleteByOrganization_Name(String name);

    Long deleteById(Long id);


}

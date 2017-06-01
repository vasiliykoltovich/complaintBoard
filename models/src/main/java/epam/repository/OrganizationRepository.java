package epam.repository;


import epam.beans.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);

    Long deleteByName(String name);


}

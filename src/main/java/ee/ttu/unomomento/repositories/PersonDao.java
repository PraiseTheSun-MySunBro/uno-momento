package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository <Person, Long> {
}

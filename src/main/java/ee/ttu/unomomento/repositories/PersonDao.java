package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {

    Person findByPersonId(@Param("person_id") Long personId);
}

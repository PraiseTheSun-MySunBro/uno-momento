package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person, Long> {

    Person findByPersonId(@Param("person_id") Long personId);
    @Query("select p from Person p")
    List<Person> findCurators(Pageable pageable);
    @Query("select p from Person p")
    List<Person> findCurators();
}

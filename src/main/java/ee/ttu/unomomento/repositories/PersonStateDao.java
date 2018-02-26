package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.PersonState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonStateDao extends JpaRepository<PersonState, Short> {

    PersonState findByPersonStateCode(@Param("person_state_code") Short personStateCode);
}

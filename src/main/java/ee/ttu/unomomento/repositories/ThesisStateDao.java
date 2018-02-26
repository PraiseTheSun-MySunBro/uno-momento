package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.ThesisState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisStateDao extends JpaRepository<ThesisState, Short> {

    ThesisState findByThesisStateCode(@Param("thesis_state_code") Short thesisStateCode);
}

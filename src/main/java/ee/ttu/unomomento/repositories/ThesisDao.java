package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDao extends JpaRepository<Thesis, Long> {

    Thesis findByThesisId(@Param("thesis_id") Long thesisId);
    Thesis findByFacultyCode(@Param("faculty_code") Short facultyCode);
    Thesis findByThesisStateCode(@Param("thesis_state_code") Short thesisStateCode);
}

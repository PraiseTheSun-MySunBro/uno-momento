package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDao extends JpaRepository<Faculty, Short> {

    Faculty findByFacultyCode(@Param("faculty_code") Short facultyCode);
}

package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.PersonFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonFacultyDao extends JpaRepository<PersonFaculty, Long> {
}
package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.RoleFacultyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleFacultyOwnerDao extends JpaRepository<RoleFacultyOwner, Long> {

    RoleFacultyOwner findByPersonId(@Param("person_id") Long personId);
    RoleFacultyOwner findByRoleFacultyCode(@Param("role_faculty_code") Short roleFacultyCode);
}

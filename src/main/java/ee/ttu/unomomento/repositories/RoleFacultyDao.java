package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.RoleFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleFacultyDao extends JpaRepository<RoleFaculty, Short> {

    RoleFaculty findByRoleFacultyCode(@Param("role_faculty_code") Short roleFacultyCode);
    RoleFaculty findByRoleCode(@Param("role_code") Short roleCode);
    RoleFaculty findByFacultyCode(@Param("faculty_code") Short facultyCode);
}

package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Short> {

    Role findByRoleCode(@Param("role_code") Short roleCode);
}

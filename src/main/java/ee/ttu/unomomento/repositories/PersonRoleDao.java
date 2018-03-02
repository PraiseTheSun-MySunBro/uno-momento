package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleDao extends JpaRepository<PersonRole, Long> {
}
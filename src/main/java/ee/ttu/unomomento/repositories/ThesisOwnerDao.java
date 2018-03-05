package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.ThesisOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisOwnerDao extends JpaRepository<ThesisOwner, Long> {
}

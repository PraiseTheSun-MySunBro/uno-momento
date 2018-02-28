package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeDao extends JpaRepository<Degree, Short> {

    Degree findByDegreeCode(@Param("degree_code") Short degreeCode);
}

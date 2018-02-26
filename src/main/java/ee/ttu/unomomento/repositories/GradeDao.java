package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDao extends JpaRepository<Grade, Short> {

    Grade findByGradeCode(@Param("grade_code") Short gradeCode);
}

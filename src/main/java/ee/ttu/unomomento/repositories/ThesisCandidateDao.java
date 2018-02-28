package ee.ttu.unomomento.repositories;

import ee.ttu.unomomento.models.ThesisCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisCandidateDao extends JpaRepository<ThesisCandidate, Long> {

    ThesisCandidate findByThesisId(@Param("thesis_id") Long thesisId);
    ThesisCandidate findByCandidateId(@Param("candidate_id") Long personId);
}

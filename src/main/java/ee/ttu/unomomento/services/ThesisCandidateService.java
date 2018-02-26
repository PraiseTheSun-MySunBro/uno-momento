package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.ThesisCandidate;
import ee.ttu.unomomento.repositories.ThesisCandidateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ThesisCandidateService {

    @Autowired
    private ThesisCandidateDao thesisCandidateDao;

    public void insert(ThesisCandidate thesisCandidate) {
        thesisCandidateDao.save(thesisCandidate);
    }

    public List<ThesisCandidate> getThesisCandidates() {
        return thesisCandidateDao.findAll();
    }
}

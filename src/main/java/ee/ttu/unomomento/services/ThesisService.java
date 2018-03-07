package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Thesis;
import ee.ttu.unomomento.repositories.ThesisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ThesisService {

    @Autowired
    private ThesisDao thesisDao;

    public void insert(Thesis thesis) {
        thesisDao.save(thesis);
    }

    public List<Thesis> getTheses() {
        return thesisDao.findAll();
    }

}

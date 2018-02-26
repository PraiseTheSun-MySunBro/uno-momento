package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.ThesisState;
import ee.ttu.unomomento.repositories.ThesisStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ThesisStateService {

    @Autowired
    private ThesisStateDao thesisStateDao;

    public void insert(ThesisState thesisState) {
        thesisStateDao.save(thesisState);
    }

    public List<ThesisState> getThesisStates() {
        return thesisStateDao.findAll();
    }
}

package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.PersonState;
import ee.ttu.unomomento.repositories.PersonStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PersonStateService {

    @Autowired
    private PersonStateDao personStateDao;

    public void insert(PersonState personState) {
        personStateDao.save(personState);
    }

    public List<PersonState> getPersonStates() {
        return personStateDao.findAll();
    }
}

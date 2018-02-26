package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Person;
import ee.ttu.unomomento.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public void insert(Person person) {
        personDao.save(person);
    }

    public List<Person> getPersons() {
        return personDao.findAll();
    }
}

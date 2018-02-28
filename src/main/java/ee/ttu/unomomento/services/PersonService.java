package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Person;
import ee.ttu.unomomento.repositories.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Person getById(Long id) {
        return personDao.findByPersonId(id);
    }

    public List<Person> getPersons() {
        return personDao.findAll();
    }

    public List<Person> getCurators(int offset, int limit) {
        Pageable pageable = new PageRequest(offset, limit);
        return personDao.findCurators(pageable);
    }

    public List<Person> getCurators() {
        return personDao.findAll();
    }
}

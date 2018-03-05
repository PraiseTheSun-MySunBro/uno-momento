package ee.ttu.unomomento.services;

import ee.ttu.unomomento.repositories.PersonFacultyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class PersonFacultyService {

    @Autowired
    private PersonFacultyDao personFacultyDao;
}

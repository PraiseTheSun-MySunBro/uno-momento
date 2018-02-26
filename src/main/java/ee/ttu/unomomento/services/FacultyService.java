package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Faculty;
import ee.ttu.unomomento.repositories.FacultyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FacultyService {

    @Autowired
    private FacultyDao facultyDao;

    public void insert(Faculty faculty) {
        facultyDao.save(faculty);
    }

    public List<Faculty> getFaculties() {
        return facultyDao.findAll();
    }
}

package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.RoleFaculty;
import ee.ttu.unomomento.repositories.RoleFacultyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleFacultyService {

    @Autowired
    private RoleFacultyDao roleFacultyDao;

    public void insert(RoleFaculty roleFaculty) {
        roleFacultyDao.save(roleFaculty);
    }

    public List<RoleFaculty> getRoleFaculties() {
        return roleFacultyDao.findAll();
    }
}

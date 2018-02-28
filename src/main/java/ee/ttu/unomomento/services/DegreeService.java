package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Degree;
import ee.ttu.unomomento.repositories.DegreeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DegreeService {

    @Autowired
    private DegreeDao degreeDao;

    public void insert(Degree degree) {
        degreeDao.save(degree);
    }

    public List<Degree> getDegrees() {
        return degreeDao.findAll();
    }
}

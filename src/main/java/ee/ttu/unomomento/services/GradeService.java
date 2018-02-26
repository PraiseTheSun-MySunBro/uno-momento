package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Grade;
import ee.ttu.unomomento.repositories.GradeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    public void insert(Grade grade) {
        gradeDao.save(grade);
    }

    public List<Grade> getGrades() {
        return gradeDao.findAll();
    }
}

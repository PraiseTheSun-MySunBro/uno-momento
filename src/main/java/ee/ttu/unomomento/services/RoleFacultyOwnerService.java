package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.RoleFacultyOwner;
import ee.ttu.unomomento.repositories.RoleFacultyOwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleFacultyOwnerService {

    @Autowired
    private RoleFacultyOwnerDao roleFacultyOwnerDao;

    public void insert(RoleFacultyOwner roleFacultyOwner) {
        roleFacultyOwnerDao.save(roleFacultyOwner);
    }

    public List<RoleFacultyOwner> getRoleFacultyOwners() {
        return roleFacultyOwnerDao.findAll();
    }
}

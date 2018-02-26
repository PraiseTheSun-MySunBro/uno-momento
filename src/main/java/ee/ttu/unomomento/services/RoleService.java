package ee.ttu.unomomento.services;

import ee.ttu.unomomento.models.Role;
import ee.ttu.unomomento.repositories.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public void insert(Role role) {
        roleDao.save(role);
    }

    public List<Role> getRoles() {
        return roleDao.findAll();
    }
}

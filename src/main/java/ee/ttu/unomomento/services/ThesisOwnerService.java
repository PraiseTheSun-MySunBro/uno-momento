package ee.ttu.unomomento.services;

import ee.ttu.unomomento.repositories.ThesisOwnerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ThesisOwnerService {

    @Autowired
    private ThesisOwnerDao thesisOwnerDao;
}

package ee.ttu.unomomento.services;


import ee.ttu.unomomento.repositories.ThesisTagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ThesisTagService {

    @Autowired
    private ThesisTagDao thesisTagDao;
}

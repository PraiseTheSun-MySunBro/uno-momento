package ee.ttu.unomomento.service;

import ee.ttu.unomomento.model.Faculty;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.Faculty.FACULTY;
import static ee.ttu.unomomento.db.tables.PersonFaculty.PERSON_FACULTY;

@Service
@Transactional
public class FacultyService {
    private final DSLContext dslContext;

    @Autowired
    public FacultyService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Faculty getFacultyByPersonId(Long person_id) {
        Result<?> result = dslContext
                .select()
                .from(FACULTY)
                .innerJoin(PERSON_FACULTY)
                .using(PERSON_FACULTY.FACULTY_CODE)
                .where(PERSON_FACULTY.PERSON_ID.eq(person_id))
                .fetch();
        try {
            return result.get(0).into(Faculty.class);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

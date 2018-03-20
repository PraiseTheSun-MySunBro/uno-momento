package ee.ttu.unomomento.service;

import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonRole.PERSON_ROLE;
import static ee.ttu.unomomento.db.tables.ThesisOwner.THESIS_OWNER;
import static ee.ttu.unomomento.db.tables.Thesis.THESIS;
import static ee.ttu.unomomento.db.tables.CuratorsWithTheses.CURATORS_WITH_THESES;

@Service
@Transactional
public class PersonService {

    private final DSLContext dslContext;

    @Autowired
    public PersonService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Result<?> getAllCuratorsWithTheses() {
        return allCuratorsQuery()
                .fetch();
    }

    public Result<?> getAllCuratorsWithThesesByPages(int p, int limit) {
        p = (p < 0) ? 0 : p;
        return allCuratorsQuery()
                .offset(p)
                .limit(limit)
                .fetch();
    }

    public Result<?> getPersonById(Long personId) {
        return dslContext
                .select()
                .from(PERSON)
                .where(PERSON.PERSON_ID.eq(personId))
                .fetch();
    }

    private SelectJoinStep<Record> allCuratorsQuery() {
        return dslContext
                .select()
                .from(CURATORS_WITH_THESES);
    }
}

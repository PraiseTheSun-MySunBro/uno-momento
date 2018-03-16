package ee.ttu.unomomento.service;

import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonRole.PERSON_ROLE;
import static ee.ttu.unomomento.db.tables.ThesisOwner.THESIS_OWNER;
import static ee.ttu.unomomento.db.tables.Thesis.THESIS;

@Service
@Transactional
public class PersonService {

    private final DSLContext dslContext;

    @Autowired
    public PersonService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Result<?> getAllCuratorsWithTheses() {
        return dslContext
                .resultQuery(
                "SELECT p.*, array_to_json(array_agg(t)) AS theses\n" +
                    "FROM person p\n" +
                    "  INNER JOIN thesis_owner USING (person_id)\n" +
                    "  INNER JOIN thesis t USING (thesis_id)\n" +
                    "  INNER JOIN person_role pr USING (person_id)\n" +
                    "WHERE t.thesis_id IN (SELECT thesis_id FROM Thesis_Owner) AND pr.role_code = 2\n" +
                    "GROUP BY p.person_id").fetch();
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

    private SelectConditionStep<Record> allCuratorsQuery() {
        return dslContext
                .select()
                .from(PERSON)
                .join(PERSON_ROLE).using(PERSON.PERSON_ID)
                .join(THESIS_OWNER).using(PERSON.PERSON_ID)
                .join(THESIS).using(THESIS_OWNER.THESIS_ID)
                .where(PERSON_ROLE.ROLE_CODE.eq((short) 2));
    }
}

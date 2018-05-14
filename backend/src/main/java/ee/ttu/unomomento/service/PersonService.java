package ee.ttu.unomomento.service;

import ee.ttu.unomomento.db.tables.PersonRole;
import ee.ttu.unomomento.model.Person;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ee.ttu.unomomento.db.Tables.PERSON_ROLE;
import static ee.ttu.unomomento.db.tables.Account.ACCOUNT;
import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonAccountOwner.PERSON_ACCOUNT_OWNER;
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

    public Result<?> getAllCuratorsWithTheses(Short facultyCode) {
        return dslContext.fetch("SELECT p.person_id,\n" +
                "  p.uni_id,\n" +
                "  p.degree_code,\n" +
                "  p.firstname,\n" +
                "  p.lastname,\n" +
                "  p.person_state_code,\n" +
                "  array_to_json(array_agg(r.*)) AS theses\n" +
                "FROM person p\n" +
                "  INNER JOIN thesis_owner tho USING (person_id)\n" +
                "  INNER JOIN person_role pr USING (person_id)\n" +
                "  INNER JOIN (\n" +
                "               SELECT t.*, d.en_name AS en_degree, d.ee_name AS ee_degree, array_agg(tag_name) AS tags\n" +
                "               FROM thesis t\n" +
                "                 INNER JOIN degree d ON t.degree_code = d.degree_code\n" +
                "                 LEFT JOIN thesis_tag USING (thesis_id)\n" +
                "               GROUP BY t.thesis_id, d.en_name, d.ee_name\n" +
                "             ) r ON r.thesis_id = tho.thesis_id\n" +
                "WHERE (pr.role_code = 2)\n" +
                "GROUP BY p.person_id;");
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

    public List<ee.ttu.unomomento.model.PersonRole> getPersonRoleByPersonId(Long personId) {
        return dslContext
                .select()
                .from(PERSON_ROLE)
                .where(PERSON_ROLE.PERSON_ID.eq(personId))
                .fetchInto(ee.ttu.unomomento.model.PersonRole.class);
    }

    public Person findPersonByAccountId(Long accountId) {
        Result<?> result = dslContext
                .select()
                .from(PERSON)
                .innerJoin(PERSON_ACCOUNT_OWNER)
                .using(PERSON.PERSON_ID)
                .where(PERSON_ACCOUNT_OWNER.ACCOUNT_ID.eq(accountId))
                .fetch();
        try {
            return result.get(0).into(Person.class);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

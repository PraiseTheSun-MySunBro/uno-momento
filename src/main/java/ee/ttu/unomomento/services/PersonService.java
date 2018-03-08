package ee.ttu.unomomento.services;

import ee.ttu.unomomento.db.tables.records.PersonRecord;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonRole.PERSON_ROLE;

@Service
@Transactional
public class PersonService {

    @Autowired
    private DSLContext dsl;

    public Result<?> getCurators() {
        return dsl
                .select()
                .from(PERSON)
                .join(PERSON_ROLE)
                .using(PERSON.PERSON_ID)
                .where(PERSON_ROLE.ROLE_CODE.eq((short) 2)).fetch();
    }
}

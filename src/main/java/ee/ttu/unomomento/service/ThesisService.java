package ee.ttu.unomomento.service;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.Thesis.THESIS;
import static ee.ttu.unomomento.db.tables.ThesisOwner.THESIS_OWNER;

@Service
@Transactional
public class ThesisService {

    @Autowired
    private DSLContext dslContext;

    public Result<?> getAllTheses() {
        return dslContext
                .select()
                .from(THESIS)
                .fetch();
    }

    public Result<?> getThesesByPersonId(Long personId) {
        return dslContext
                .select()
                .from(THESIS)
                .join(THESIS_OWNER).using(THESIS.THESIS_ID)
                .where(THESIS_OWNER.PERSON_ID.eq(personId))
                .fetch();
    }
}

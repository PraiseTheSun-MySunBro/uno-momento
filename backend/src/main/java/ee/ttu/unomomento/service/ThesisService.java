package ee.ttu.unomomento.service;

import ee.ttu.unomomento.db.tables.records.ThesisOwnerRecord;
import ee.ttu.unomomento.db.tables.records.ThesisRecord;
import ee.ttu.unomomento.db.tables.records.ThesisTagRecord;
import ee.ttu.unomomento.model.Account;
import ee.ttu.unomomento.model.Thesis;
import ee.ttu.unomomento.model.ThesisOwner;
import ee.ttu.unomomento.model.ThesisTag;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static ee.ttu.unomomento.db.tables.Thesis.THESIS;
import static ee.ttu.unomomento.db.tables.ThesisOwner.THESIS_OWNER;
import static ee.ttu.unomomento.db.tables.ThesisTag.THESIS_TAG;

@Service
@Transactional
public class ThesisService {

    private final DSLContext dslContext;
    private final AccountService accountService;

    @Autowired
    public ThesisService(DSLContext dslContext, AccountService accountService) {
        this.dslContext = dslContext;
        this.accountService = accountService;
    }

    public void save(@NotNull Thesis thesis, String username) {
        Account account = accountService.findAccountByUsername(username);
        Long accountId = account.getAccountId();

        thesis.setFacultyCode((short) 3);  // change to logged in user faculty code
        ThesisRecord thesisRecord = dslContext.newRecord(THESIS, thesis);
        thesisRecord.store();

        Long thesisId = thesisRecord.get(THESIS.THESIS_ID);
        ThesisOwnerRecord thesisOwnerRecord = dslContext.newRecord(THESIS_OWNER,
                new ThesisOwner(thesisId, accountId, (short) 1));  // Change roleCode to authorized
        thesisOwnerRecord.store();

        ThesisTagRecord thesisTagRecord = dslContext.newRecord(THESIS_TAG,
                new ThesisTag(thesisId, "Test"));  // Change tag names to list
        thesisTagRecord.store();
    }

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

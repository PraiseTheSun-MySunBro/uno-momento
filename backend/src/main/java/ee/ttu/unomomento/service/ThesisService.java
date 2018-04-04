package ee.ttu.unomomento.service;

import ee.ttu.unomomento.db.tables.records.ThesisOwnerRecord;
import ee.ttu.unomomento.db.tables.records.ThesisRecord;
import ee.ttu.unomomento.db.tables.records.ThesisTagRecord;
import ee.ttu.unomomento.model.Account;
import ee.ttu.unomomento.model.Thesis;
import ee.ttu.unomomento.model.ThesisOwner;
import ee.ttu.unomomento.model.ThesisTag;
import ee.ttu.unomomento.model.template.AccountPersonInformation;
import ee.ttu.unomomento.model.template.AddThesis;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.Objects;

import static ee.ttu.unomomento.db.tables.Account.ACCOUNT;
import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonAccountOwner.PERSON_ACCOUNT_OWNER;
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

    public boolean save(AddThesis thesisTemplate, String username) {
        AccountPersonInformation account = accountService.findAccountByUsernameDegreeFacultyRoleCodes(username, thesisTemplate.getDegreeCode(),
                thesisTemplate.getFacultyCode(), thesisTemplate.getRoleCode());

        if (account == null) return false;

        Long accountId = account.getAccountId();
        Thesis thesis = new Thesis(null, thesisTemplate.getSupervisorName(), thesisTemplate.getFacultyCode(), null, thesisTemplate.getDegreeCode(),
                thesisTemplate.getEeTitle(), thesisTemplate.getEnTitle(), thesisTemplate.getEeDescription(), thesisTemplate.getEnDescription());
        ThesisRecord thesisRecord = dslContext.newRecord(THESIS, thesis);
        thesisRecord.insert();

        Long thesisId = thesisRecord.getThesisId();
        ThesisOwnerRecord thesisOwnerRecord = dslContext.newRecord(THESIS_OWNER,
                new ThesisOwner(thesisId, accountId, thesisTemplate.getRoleCode()));
        thesisOwnerRecord.insert();

        for (String tag : thesisTemplate.getTags()) {
            ThesisTagRecord thesisTagRecord = dslContext.newRecord(THESIS_TAG,
                    new ThesisTag(thesisId, tag));
            thesisTagRecord.insert();
        }

        return true;
    }

    public boolean makeInactive(Long thesisId, String username) {
        Object resultName = dslContext
                .select(ACCOUNT.USERNAME)
                .from(THESIS_OWNER)
                .innerJoin(PERSON).using(PERSON.PERSON_ID)
                .innerJoin(PERSON_ACCOUNT_OWNER).using(PERSON.PERSON_ID)
                .innerJoin(ACCOUNT).using(ACCOUNT.ACCOUNT_ID)
                .where(THESIS_OWNER.THESIS_ID.eq(thesisId))
                .fetchOne("username");

        if (resultName == null || !resultName.toString().equals(username)) {
            return false;
        }

        dslContext
            .update(THESIS)
            .set(THESIS.THESIS_STATE_CODE, (short) 2)
            .where(THESIS.THESIS_ID.eq(thesisId))
            .execute();

        return true;
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

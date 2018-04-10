package ee.ttu.unomomento.service;

import ee.ttu.unomomento.db.tables.records.*;
import ee.ttu.unomomento.model.*;
import ee.ttu.unomomento.model.template.AccountPersonInformation;
import ee.ttu.unomomento.model.template.UserRegistration;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.AccountState.ACCOUNT_STATE;
import static ee.ttu.unomomento.db.tables.Account.ACCOUNT;
import static ee.ttu.unomomento.db.tables.Degree.DEGREE;
import static ee.ttu.unomomento.db.tables.Faculty.FACULTY;
import static ee.ttu.unomomento.db.tables.Person.PERSON;
import static ee.ttu.unomomento.db.tables.PersonAccountOwner.PERSON_ACCOUNT_OWNER;
import static ee.ttu.unomomento.db.tables.PersonFaculty.PERSON_FACULTY;
import static ee.ttu.unomomento.db.tables.PersonRole.PERSON_ROLE;
import static ee.ttu.unomomento.db.tables.Role.ROLE;

@Service
@Transactional
public class AccountService {

    private final DSLContext dslContext;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(DSLContext dslContext, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dslContext = dslContext;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveAccount(UserRegistration userRegistration) {
        Account account = new Account();
        account.setUsername(userRegistration.getUsername());
        account.setPassword(bCryptPasswordEncoder.encode(userRegistration.getPassword()));
        account.setEmail(userRegistration.getEmail().toLowerCase());

        AccountRecord accountRecord = dslContext.newRecord(ACCOUNT, account);
        accountRecord.insert();

        Person person = new Person();
        person.setDegreeCode(userRegistration.getDegreeCode());
        person.setFirstname(userRegistration.getFirstname());
        person.setLastname(userRegistration.getLastname());
        person.setUniId(userRegistration.getUniId().toLowerCase());

        PersonRecord personRecord = dslContext.newRecord(PERSON, person);
        personRecord.insert();

        Long accountId = accountRecord.getAccountId();
        Long personId = personRecord.getPersonId();

        PersonAccountOwnerRecord personAccountOwnerRecord = dslContext
                .newRecord(PERSON_ACCOUNT_OWNER, new PersonAccountOwner(personId, accountId));
        personAccountOwnerRecord.insert();

        PersonRoleRecord personRoleRecord = dslContext
                .newRecord(PERSON_ROLE, new PersonRole(personId, userRegistration.getRoleCode()));
        personRoleRecord.insert();

        PersonFacultyRecord personFacultyRecord = dslContext
                .newRecord(PERSON_FACULTY, new PersonFaculty(personId, userRegistration.getFacultyCode()));
        personFacultyRecord.insert();
    }

    public Account findAccountByUsername(String username) {
        Result<?> result = dslContext
                .select()
                .from(ACCOUNT)
                .where(ACCOUNT.USERNAME.eq(username))
                .fetch();
        try {
            return result.get(0).into(Account.class);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public AccountPersonInformation findAccountPersonInformationByUsername(String username) {
        Result<?> result = dslContext
                .select(ACCOUNT.ACCOUNT_ID, ACCOUNT.USERNAME, ACCOUNT.EMAIL, ACCOUNT.ACCOUNT_STATE_CODE,
                        PERSON.PERSON_ID, PERSON.FIRSTNAME, PERSON.LASTNAME, PERSON.DEGREE_CODE,
                        PERSON.UNI_ID, PERSON_FACULTY.FACULTY_CODE, FACULTY.EE_NAME.as("faculty_ee_name"),
                        FACULTY.EN_NAME.as("faculty_en_name"), PERSON_ROLE.ROLE_CODE, ROLE.EE_NAME.as("role_ee_name"),
                        ROLE.EN_NAME.as("role_en_name"), ACCOUNT_STATE.EE_NAME.as("account_state_ee_name"),
                        ACCOUNT_STATE.EN_NAME.as("account_state_en_name"), DEGREE.EE_NAME.as("degree_ee_name"),
                        DEGREE.EN_NAME.as("degree_en_name"))
                .from(PERSON)
                .innerJoin(PERSON_ACCOUNT_OWNER).using(PERSON.PERSON_ID)
                .innerJoin(ACCOUNT).using(PERSON_ACCOUNT_OWNER.ACCOUNT_ID)
                .innerJoin(PERSON_FACULTY).using(PERSON.PERSON_ID)
                .innerJoin(FACULTY).using(PERSON_FACULTY.FACULTY_CODE)
                .innerJoin(PERSON_ROLE).using(PERSON.PERSON_ID)
                .innerJoin(ROLE).using(PERSON_ROLE.ROLE_CODE)
                .innerJoin(ACCOUNT_STATE).using(ACCOUNT.ACCOUNT_STATE_CODE)
                .innerJoin(DEGREE).using(PERSON.DEGREE_CODE)
                .where(ACCOUNT.USERNAME.lower().eq(username.toLowerCase()))
                .fetch();
        try {
            return result.get(0).into(AccountPersonInformation.class);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public AccountPersonInformation findAccountByUsernameFacultyRoleCodes(String username, Short degreeCode, Short facultyCode, Short roleCode) {
        Result<?> result = dslContext
                .select(ACCOUNT.ACCOUNT_ID, ACCOUNT.USERNAME, PERSON.PERSON_ID, PERSON.FIRSTNAME, PERSON.LASTNAME, PERSON.DEGREE_CODE,
                        PERSON.UNI_ID, PERSON_FACULTY.FACULTY_CODE, PERSON_ROLE.ROLE_CODE)
                .from(PERSON)
                .innerJoin(PERSON_ACCOUNT_OWNER).using(PERSON.PERSON_ID)
                .innerJoin(ACCOUNT).using(PERSON_ACCOUNT_OWNER.ACCOUNT_ID)
                .innerJoin(PERSON_FACULTY).using(PERSON.PERSON_ID)
                .innerJoin(PERSON_ROLE).using(PERSON.PERSON_ID)
                .innerJoin(ACCOUNT_STATE).using(ACCOUNT.ACCOUNT_STATE_CODE)
                .where(ACCOUNT.USERNAME.lower().eq(username.toLowerCase())
                        .and(PERSON_FACULTY.FACULTY_CODE.eq(facultyCode))
                        .and(PERSON_ROLE.ROLE_CODE.eq(roleCode)))
                .fetch();
        try {
            return result.get(0).into(AccountPersonInformation.class);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

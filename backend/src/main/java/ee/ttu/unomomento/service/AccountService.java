package ee.ttu.unomomento.service;

import ee.ttu.unomomento.db.tables.pojos.Account;
import ee.ttu.unomomento.db.tables.records.AccountRecord;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ee.ttu.unomomento.db.tables.Account.ACCOUNT;

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

    public void saveAccount(Account account) throws DataAccessException {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        AccountRecord accountRecord = dslContext.newRecord(ACCOUNT, account);
        accountRecord.store();
    }
}

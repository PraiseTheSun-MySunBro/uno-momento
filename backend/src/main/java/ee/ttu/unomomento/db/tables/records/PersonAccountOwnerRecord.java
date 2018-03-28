/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.records;


import ee.ttu.unomomento.db.tables.PersonAccountOwner;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonAccountOwnerRecord extends UpdatableRecordImpl<PersonAccountOwnerRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1803660608;

    /**
     * Setter for <code>public.person_account_owner.account_id</code>.
     */
    public PersonAccountOwnerRecord setAccountId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.person_account_owner.account_id</code>.
     */
    public Long getAccountId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.person_account_owner.person_id</code>.
     */
    public PersonAccountOwnerRecord setPersonId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.person_account_owner.person_id</code>.
     */
    public Long getPersonId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return PersonAccountOwner.PERSON_ACCOUNT_OWNER.ACCOUNT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return PersonAccountOwner.PERSON_ACCOUNT_OWNER.PERSON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getAccountId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getPersonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonAccountOwnerRecord value1(Long value) {
        setAccountId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonAccountOwnerRecord value2(Long value) {
        setPersonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonAccountOwnerRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonAccountOwnerRecord
     */
    public PersonAccountOwnerRecord() {
        super(PersonAccountOwner.PERSON_ACCOUNT_OWNER);
    }

    /**
     * Create a detached, initialised PersonAccountOwnerRecord
     */
    public PersonAccountOwnerRecord(Long accountId, Long personId) {
        super(PersonAccountOwner.PERSON_ACCOUNT_OWNER);

        set(0, accountId);
        set(1, personId);
    }
}
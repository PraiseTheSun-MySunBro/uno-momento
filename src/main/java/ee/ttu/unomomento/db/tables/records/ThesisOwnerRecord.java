/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.records;


import ee.ttu.unomomento.db.tables.ThesisOwner;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class ThesisOwnerRecord extends UpdatableRecordImpl<ThesisOwnerRecord> implements Record3<Long, Long, Short> {

    private static final long serialVersionUID = -1265866449;

    /**
     * Setter for <code>public.thesis_owner.thesis_id</code>.
     */
    public ThesisOwnerRecord setThesisId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.thesis_owner.thesis_id</code>.
     */
    public Long getThesisId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.thesis_owner.person_id</code>.
     */
    public ThesisOwnerRecord setPersonId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.thesis_owner.person_id</code>.
     */
    public Long getPersonId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.thesis_owner.role_code</code>.
     */
    public ThesisOwnerRecord setRoleCode(Short value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.thesis_owner.role_code</code>.
     */
    public Short getRoleCode() {
        return (Short) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, Long, Short> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Long, Long, Short> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return ThesisOwner.THESIS_OWNER.THESIS_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return ThesisOwner.THESIS_OWNER.PERSON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field3() {
        return ThesisOwner.THESIS_OWNER.ROLE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getThesisId();
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
    public Short value3() {
        return getRoleCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThesisOwnerRecord value1(Long value) {
        setThesisId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThesisOwnerRecord value2(Long value) {
        setPersonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThesisOwnerRecord value3(Short value) {
        setRoleCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThesisOwnerRecord values(Long value1, Long value2, Short value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ThesisOwnerRecord
     */
    public ThesisOwnerRecord() {
        super(ThesisOwner.THESIS_OWNER);
    }

    /**
     * Create a detached, initialised ThesisOwnerRecord
     */
    public ThesisOwnerRecord(Long thesisId, Long personId, Short roleCode) {
        super(ThesisOwner.THESIS_OWNER);

        set(0, thesisId);
        set(1, personId);
        set(2, roleCode);
    }
}
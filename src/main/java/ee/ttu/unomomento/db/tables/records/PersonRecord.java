/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.records;


import ee.ttu.unomomento.db.tables.Person;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
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
public class PersonRecord extends UpdatableRecordImpl<PersonRecord> implements Record9<Long, Short, String, String, Short, String, String, String, Timestamp> {

    private static final long serialVersionUID = -1785749843;

    /**
     * Setter for <code>public.person.person_id</code>.
     */
    public PersonRecord setPersonId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.person.person_id</code>.
     */
    public Long getPersonId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.person.degree_code</code>.
     */
    public PersonRecord setDegreeCode(Short value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.person.degree_code</code>.
     */
    public Short getDegreeCode() {
        return (Short) get(1);
    }

    /**
     * Setter for <code>public.person.firstname</code>.
     */
    public PersonRecord setFirstname(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.person.firstname</code>.
     */
    public String getFirstname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.person.lastname</code>.
     */
    public PersonRecord setLastname(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.person.lastname</code>.
     */
    public String getLastname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.person.person_state_code</code>.
     */
    public PersonRecord setPersonStateCode(Short value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.person.person_state_code</code>.
     */
    public Short getPersonStateCode() {
        return (Short) get(4);
    }

    /**
     * Setter for <code>public.person.uni_id</code>.
     */
    public PersonRecord setUniId(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.person.uni_id</code>.
     */
    public String getUniId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.person.email</code>.
     */
    public PersonRecord setEmail(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.person.email</code>.
     */
    public String getEmail() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.person.password</code>.
     */
    public PersonRecord setPassword(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>public.person.password</code>.
     */
    public String getPassword() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.person.reg_time</code>.
     */
    public PersonRecord setRegTime(Timestamp value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>public.person.reg_time</code>.
     */
    public Timestamp getRegTime() {
        return (Timestamp) get(8);
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
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<Long, Short, String, String, Short, String, String, String, Timestamp> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<Long, Short, String, String, Short, String, String, String, Timestamp> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Person.PERSON.PERSON_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field2() {
        return Person.PERSON.DEGREE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Person.PERSON.FIRSTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Person.PERSON.LASTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Short> field5() {
        return Person.PERSON.PERSON_STATE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Person.PERSON.UNI_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Person.PERSON.EMAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Person.PERSON.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return Person.PERSON.REG_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getPersonId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value2() {
        return getDegreeCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getFirstname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getLastname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short value5() {
        return getPersonStateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getUniId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getEmail();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getRegTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value1(Long value) {
        setPersonId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value2(Short value) {
        setDegreeCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value3(String value) {
        setFirstname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value4(String value) {
        setLastname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value5(Short value) {
        setPersonStateCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value6(String value) {
        setUniId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value7(String value) {
        setEmail(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value8(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord value9(Timestamp value) {
        setRegTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRecord values(Long value1, Short value2, String value3, String value4, Short value5, String value6, String value7, String value8, Timestamp value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonRecord
     */
    public PersonRecord() {
        super(Person.PERSON);
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(Long personId, Short degreeCode, String firstname, String lastname, Short personStateCode, String uniId, String email, String password, Timestamp regTime) {
        super(Person.PERSON);

        set(0, personId);
        set(1, degreeCode);
        set(2, firstname);
        set(3, lastname);
        set(4, personStateCode);
        set(5, uniId);
        set(6, email);
        set(7, password);
        set(8, regTime);
    }
}

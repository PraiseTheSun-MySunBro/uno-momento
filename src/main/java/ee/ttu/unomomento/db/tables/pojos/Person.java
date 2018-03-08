/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class Person implements Serializable {

    private static final long serialVersionUID = -1974317302;

    private final Long      personId;
    private final Short     degreeCode;
    private final String    firstname;
    private final String    lastname;
    private final Short     personStateCode;
    private final String    uniId;
    private final String    email;
    private final String    password;
    private final Timestamp regTime;

    public Person(Person value) {
        this.personId = value.personId;
        this.degreeCode = value.degreeCode;
        this.firstname = value.firstname;
        this.lastname = value.lastname;
        this.personStateCode = value.personStateCode;
        this.uniId = value.uniId;
        this.email = value.email;
        this.password = value.password;
        this.regTime = value.regTime;
    }

    public Person(
        Long      personId,
        Short     degreeCode,
        String    firstname,
        String    lastname,
        Short     personStateCode,
        String    uniId,
        String    email,
        String    password,
        Timestamp regTime
    ) {
        this.personId = personId;
        this.degreeCode = degreeCode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.personStateCode = personStateCode;
        this.uniId = uniId;
        this.email = email;
        this.password = password;
        this.regTime = regTime;
    }

    public Long getPersonId() {
        return this.personId;
    }

    public Short getDegreeCode() {
        return this.degreeCode;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Short getPersonStateCode() {
        return this.personStateCode;
    }

    public String getUniId() {
        return this.uniId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Timestamp getRegTime() {
        return this.regTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Person (");

        sb.append(personId);
        sb.append(", ").append(degreeCode);
        sb.append(", ").append(firstname);
        sb.append(", ").append(lastname);
        sb.append(", ").append(personStateCode);
        sb.append(", ").append(uniId);
        sb.append(", ").append(email);
        sb.append(", ").append(password);
        sb.append(", ").append(regTime);

        sb.append(")");
        return sb.toString();
    }
}
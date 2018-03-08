/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * 1 -- Student, 2 -- Curator
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Role implements Serializable {

    private static final long serialVersionUID = -1333010200;

    private final Short  roleCode;
    private final String enName;
    private final String eeName;

    public Role(Role value) {
        this.roleCode = value.roleCode;
        this.enName = value.enName;
        this.eeName = value.eeName;
    }

    public Role(
        Short  roleCode,
        String enName,
        String eeName
    ) {
        this.roleCode = roleCode;
        this.enName = enName;
        this.eeName = eeName;
    }

    public Short getRoleCode() {
        return this.roleCode;
    }

    public String getEnName() {
        return this.enName;
    }

    public String getEeName() {
        return this.eeName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Role (");

        sb.append(roleCode);
        sb.append(", ").append(enName);
        sb.append(", ").append(eeName);

        sb.append(")");
        return sb.toString();
    }
}

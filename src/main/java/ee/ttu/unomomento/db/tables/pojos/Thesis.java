/*
 * This file is generated by jOOQ.
*/
package ee.ttu.unomomento.db.tables.pojos;


import java.io.Serializable;

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
public class Thesis implements Serializable {

    private static final long serialVersionUID = 1234888837;

    private final Long   thesisId;
    private final String supervisorName;
    private final Short  facultyCode;
    private final Short  thesisStateCode;
    private final String eeTitle;
    private final String enTitle;
    private final String eeDescription;
    private final String enDescription;

    public Thesis(Thesis value) {
        this.thesisId = value.thesisId;
        this.supervisorName = value.supervisorName;
        this.facultyCode = value.facultyCode;
        this.thesisStateCode = value.thesisStateCode;
        this.eeTitle = value.eeTitle;
        this.enTitle = value.enTitle;
        this.eeDescription = value.eeDescription;
        this.enDescription = value.enDescription;
    }

    public Thesis(
        Long   thesisId,
        String supervisorName,
        Short  facultyCode,
        Short  thesisStateCode,
        String eeTitle,
        String enTitle,
        String eeDescription,
        String enDescription
    ) {
        this.thesisId = thesisId;
        this.supervisorName = supervisorName;
        this.facultyCode = facultyCode;
        this.thesisStateCode = thesisStateCode;
        this.eeTitle = eeTitle;
        this.enTitle = enTitle;
        this.eeDescription = eeDescription;
        this.enDescription = enDescription;
    }

    public Long getThesisId() {
        return this.thesisId;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    public Short getFacultyCode() {
        return this.facultyCode;
    }

    public Short getThesisStateCode() {
        return this.thesisStateCode;
    }

    public String getEeTitle() {
        return this.eeTitle;
    }

    public String getEnTitle() {
        return this.enTitle;
    }

    public String getEeDescription() {
        return this.eeDescription;
    }

    public String getEnDescription() {
        return this.enDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Thesis (");

        sb.append(thesisId);
        sb.append(", ").append(supervisorName);
        sb.append(", ").append(facultyCode);
        sb.append(", ").append(thesisStateCode);
        sb.append(", ").append(eeTitle);
        sb.append(", ").append(enTitle);
        sb.append(", ").append(eeDescription);
        sb.append(", ").append(enDescription);

        sb.append(")");
        return sb.toString();
    }
}

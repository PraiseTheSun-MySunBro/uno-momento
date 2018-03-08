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
public class ThesisTag implements Serializable {

    private static final long serialVersionUID = -276616992;

    private final Long   thesisId;
    private final String tagName;

    public ThesisTag(ThesisTag value) {
        this.thesisId = value.thesisId;
        this.tagName = value.tagName;
    }

    public ThesisTag(
        Long   thesisId,
        String tagName
    ) {
        this.thesisId = thesisId;
        this.tagName = tagName;
    }

    public Long getThesisId() {
        return this.thesisId;
    }

    public String getTagName() {
        return this.tagName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ThesisTag (");

        sb.append(thesisId);
        sb.append(", ").append(tagName);

        sb.append(")");
        return sb.toString();
    }
}

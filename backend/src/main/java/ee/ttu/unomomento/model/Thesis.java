package ee.ttu.unomomento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thesis {
    private Long   thesisId;
    private String supervisorName;
    private Short  facultyCode;
    private Short  thesisStateCode;
    private Short  degreeCode;
    private String eeTitle;
    private String enTitle;
    private String eeDescription;
    private String enDescription;

    @Override
    public String toString() {

        return String.format("Thesis (%d, %s, %s, %s, %s, %s, %s, %s, %s)", thesisId, supervisorName, facultyCode,
                thesisStateCode, degreeCode, eeTitle, enTitle, eeDescription, enDescription);
    }
}

package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thesisId;

    private String supervisorName;
    private Short facultyCode;
    private Short thesisStateCode;
    private String eeTitle;
    private String enTitle;
    private String eeDescription;
    private String enDescription;

    @Override
    public String toString() {
        return String.format("{%d} {%s} {%s} {%s} {%s}", thesisId, eeTitle, enTitle, eeDescription, enDescription);
    }
}

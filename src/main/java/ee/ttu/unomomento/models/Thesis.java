package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Thesis {

    private Long thesisId;
    private Long curatorId;
    private Long supervisorId;
    private Short facultyCode;
    private Short thesisStateCode;
    private String eeTitle;
    private String enTitle;
    private String eeDescription;
    private String enDescription;
}

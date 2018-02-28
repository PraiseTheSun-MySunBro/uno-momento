package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thesisId;

    private String supervisorName;
    private Short facultyCode;
    private Short thesisStateCode;
    private String eeTitle;
    private String enTitle;
    private String eeDescription;
    private String enDescription;
}

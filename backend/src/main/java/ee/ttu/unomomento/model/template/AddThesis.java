package ee.ttu.unomomento.model.template;

import ee.ttu.unomomento.model.PersonFaculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddThesis implements Serializable {

    private Short facultyCode;
    private Short degreeCode;
    private Short roleCode;

    private String eeTitle;
    private String enTitle;
    private String enDescription;
    private String eeDescription;
    private String supervisorName;
    private List<String> tags;
}

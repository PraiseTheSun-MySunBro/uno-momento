package ee.ttu.unomomento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonFaculty implements Serializable {
    private Long  personId;
    private Short facultyCode;

    @Override
    public String toString() {
        return String.format("PersonFaculty (%d, %s)", personId, facultyCode);
    }
}

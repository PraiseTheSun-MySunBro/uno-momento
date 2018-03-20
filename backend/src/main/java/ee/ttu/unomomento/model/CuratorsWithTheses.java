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
public class CuratorsWithTheses implements Serializable {
    private Long   personId;
    private String uniId;
    private Short  degreeCode;
    private String firstname;
    private String lastname;
    private Short  personStateCode;
    private Object theses;

    @Override
    public String toString() {
        return String.format("CuratorsWithTheses (%d, %s, %s, %s, %s, %s, %s)", personId, uniId, degreeCode,
                firstname, lastname, personStateCode, theses);
    }
}

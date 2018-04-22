package ee.ttu.unomomento.model;


import lombok.*;

import java.io.Serializable;

@Data
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
    private String[] tags;
}

package ee.ttu.unomomento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * 1 -- No Degree, 2 -- Bachelor, 3 -- Master, 4 -- Doctoral, 5 -- Applied 
 * Higher Education
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Degree implements Serializable {
    private Short  degreeCode;
    private String eeName;
    private String enName;

    @Override
    public String toString() {
        return String.format("Degree (%s, %s, %s)", degreeCode, eeName, enName);
    }
}

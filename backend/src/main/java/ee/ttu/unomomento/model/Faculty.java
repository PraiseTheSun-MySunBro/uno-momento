package ee.ttu.unomomento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 1 -- School of Business and Governance, 2 -- School of Engineering, 3 -- 
 * School of Information Technologies, 4 -- School of Science, 5 -- Estonian 
 * Maritime Academy
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Faculty implements Serializable {
    private Short  facultyCode;
    private String eeName;
    private String enName;

    @Override
    public String toString() {
        return String.format("Faculty (%s, %s, %s)", facultyCode, eeName, enName);
    }
}

package ee.ttu.unomomento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 1 -- Student, 2 -- Curator
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Short  roleCode;
    private String enName;
    private String eeName;

    @Override
    public String toString() {
        return String.format("Role (%s, %s, %s)", roleCode, enName, eeName);
    }
}

package ee.ttu.unomomento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 1 -- Active, 2 - Inactive, 3 - Reserved
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThesisState implements Serializable {
    private Short  thesisStateCode;
    private String eeName;
    private String enName;

    @Override
    public String toString() {
        return String.format("ThesisState (%s, %s, %s)", thesisStateCode, eeName, enName);
    }
}

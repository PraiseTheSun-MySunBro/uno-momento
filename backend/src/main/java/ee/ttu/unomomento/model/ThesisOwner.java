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
public class ThesisOwner implements Serializable {
    private Long  thesisId;
    private Long  personId;
    private Short roleCode;

    @Override
    public String toString() {
        return String.format("ThesisOwner (%d, %d, %s)", thesisId, personId, roleCode);
    }
}

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
public class PersonRole implements Serializable {
    private Long  personId;
    private Short roleCode;

    @Override
    public String toString() {
        return String.format("PersonRole (%d, %s)", personId, roleCode);
    }
}

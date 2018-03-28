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
public class PersonAccountOwner implements Serializable {
    private Long personId;
    private Long accountId;

    @Override
    public String toString() {
        return String.format("PersonAccountOwner (%s, %s)", personId, accountId);
    }
}

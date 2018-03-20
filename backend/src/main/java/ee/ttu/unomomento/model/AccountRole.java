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
public class AccountRole implements Serializable {

    private Short  accountRoleCode;
    private String roleName;

    @Override
    public String toString() {
        return String.format("AccountRole (%s, %s)", accountRoleCode, roleName);
    }
}

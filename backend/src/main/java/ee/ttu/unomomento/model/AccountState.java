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
public class AccountState implements Serializable {
    private Short  accountStateCode;
    private String eeName;
    private String enName;

    @Override
    public String toString() {
        return String.format("AccountState (%s, %s, %s)", accountStateCode, eeName, enName);
    }
}

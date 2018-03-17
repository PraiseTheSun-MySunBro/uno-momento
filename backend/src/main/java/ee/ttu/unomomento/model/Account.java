package ee.ttu.unomomento.model;

import ee.ttu.unomomento.annotation.ExcludeSerialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private Long      accountId;
    private String    username;
    @ExcludeSerialization
    private String    password;
    private String    email;
    private Short     accountStateCode;
    private Short     accountRoleCode;
    private Timestamp regTime;

    @Override
    public String toString() {
        return String.format("Account (%d, %s, %s, %s, %s, %s, %s)", accountId, username, password, email,
                accountStateCode, accountRoleCode, regTime);
    }
}

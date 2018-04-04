package ee.ttu.unomomento.model.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration implements Serializable {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String uniId;
    private Short roleCode;
    private Short degreeCode;
    private Short facultyCode;
}

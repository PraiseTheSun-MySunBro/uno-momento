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
public class AccountPersonInformation implements Serializable {

    private Long accountId;
    private Long personId;
    private Short accountStateCode;
    private Short degreeCode;
    private Short facultyCode;
    private Short roleCode;
    private String uniId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String facultyEeName;
    private String facultyEnName;
    private String roleEnName;
    private String roleEeName;
    private String accountStateEeName;
    private String accountStateEnName;
    private String degreeEnName;
    private String degreeEeName;
}

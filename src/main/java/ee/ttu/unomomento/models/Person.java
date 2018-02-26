package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    private Short gradeCode;
    private String firstname;
    private String lastname;
    private Short personStateCode;
    private String uniid;
    private String email;
    private String password;
    private Date regTime;

    // Just for testing method
    @Override
    public String toString() {
        return String.format("{%d} {%s} {%s} {%s}", personId, uniid, email, regTime);
    }
}

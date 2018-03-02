package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    private Short degreeCode;
    private String firstname;
    private String lastname;
    private Short personStateCode;
    private String uniId;
    private String email;
    private String password;
    private Date regTime;

    @OneToMany
    @JoinTable(name = "ThesisOwner", joinColumns = @JoinColumn(name = "personId"), inverseJoinColumns = @JoinColumn(name = "thesisId"))
    private Set<Thesis> theses;

    // Just for testing method
    @Override
    public String toString() {
        return String.format("{%d} {%s} {%s} {%s} {%s}", personId, uniId, email, password, regTime);
    }
}

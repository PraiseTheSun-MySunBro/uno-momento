package ee.ttu.unomomento.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@IdClass(ThesisOwner.class)
public class ThesisOwner implements Serializable {

    @Id
    private Long thesisId;

    @Id
    private Long personId;

    private Short roleCode;
}

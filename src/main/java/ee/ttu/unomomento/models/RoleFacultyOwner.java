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
@IdClass(RoleFacultyOwner.class)
public class RoleFacultyOwner implements Serializable {

    @Id
    private Long personId;

    @Id
    private Short roleFacultyCode;
}

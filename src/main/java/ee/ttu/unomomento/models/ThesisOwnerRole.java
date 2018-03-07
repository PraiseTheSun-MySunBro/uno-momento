package ee.ttu.unomomento.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@IdClass(ThesisOwnerRole.class)
public class ThesisOwnerRole implements Serializable {

    @Id
    private Long personId;

    @Id
    private Long thesisId;

    private Short personRoleCode;
}
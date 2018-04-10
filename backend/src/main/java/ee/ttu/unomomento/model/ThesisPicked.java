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
public class ThesisPicked implements Serializable {
    private Long thesisId;
    private Long personId;
    private Short degreeCode;
}

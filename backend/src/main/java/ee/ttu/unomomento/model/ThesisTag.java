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
public class ThesisTag implements Serializable {
    private Long   thesisId;
    private String tagName;

    @Override
    public String toString() {
        return String.format("ThesisTag (%d, %s)", thesisId, tagName);
    }
}

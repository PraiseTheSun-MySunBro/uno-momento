package ee.ttu.unomomento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceDTO implements Serializable {
    private Long thesisId;
    private String eeTitle;
    private String enTitle;
    private String eeDescription;
    private String enDescription;
    private String fullName;
    private String supervisorName;
    private Short thesisStateCode;
    private Date regTime;
    private String[] tags;
}

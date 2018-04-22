package ee.ttu.unomomento.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WorkplaceQueryDTO implements Serializable {
    private WorkplaceDTO pickedThesis;
    private List<WorkplaceDTO> myOwnTheses;
    private List<WorkplaceDTO> myCandidates;
}

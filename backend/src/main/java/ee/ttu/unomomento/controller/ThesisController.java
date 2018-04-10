package ee.ttu.unomomento.controller;

import com.google.gson.Gson;
import ee.ttu.unomomento.dto.WorkplaceDTO;
import ee.ttu.unomomento.dto.WorkplaceQueryDTO;
import ee.ttu.unomomento.model.template.AddThesis;
import ee.ttu.unomomento.security.TokenAuthenticationService;
import ee.ttu.unomomento.service.ThesisService;
import ee.ttu.unomomento.validator.AddThesisValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class ThesisController {

    private final ThesisService thesisService;
    private final Gson gson;

    @Autowired
    public ThesisController(ThesisService thesisService, Gson gson) {
        this.thesisService = thesisService;
        this.gson = gson;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new AddThesisValidator());
    }

    @PostMapping(value = "/api/thesis")
    public ResponseEntity<?> addThesis(@Valid @RequestBody AddThesis thesis, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        if (authentication == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String username = authentication.getName();

        if (thesisService.save(thesis, username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/thesis/edit")
    public ResponseEntity<?> editThesis(@Valid @RequestBody AddThesis thesis, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        if (authentication == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String username = authentication.getName();

        if (thesisService.update(thesis, username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/thesis/{thesisId}/inactive")
    public ResponseEntity<?> inactiveThesis(@PathVariable Long thesisId, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        if (authentication == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String username = authentication.getName();

        if (thesisService.makeInactive(thesisId, username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/api/thesis/workplace")
    public ResponseEntity<?> getWorkplaceTheses(HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        if (authentication == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        String username = authentication.getName();

        WorkplaceQueryDTO workplaceQueryDTO = new WorkplaceQueryDTO();
        WorkplaceDTO myPickedThesis = thesisService.getMyPickedThesis(username);
        List<WorkplaceDTO> myOwnTheses = thesisService.getAllMyOwnTheses(username);
        List<WorkplaceDTO> myCandidates = thesisService.getAllMyCandidateTheses(username);
        workplaceQueryDTO.setPickedThesis(myPickedThesis);
        workplaceQueryDTO.setMyOwnTheses(myOwnTheses);
        workplaceQueryDTO.setMyCandidates(myCandidates);

        return new ResponseEntity<>(gson.toJson(workplaceQueryDTO), HttpStatus.OK);
    }
}

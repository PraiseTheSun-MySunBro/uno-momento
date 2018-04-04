package ee.ttu.unomomento.controller;

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

@Slf4j
@RestController
public class ThesisController {

    private final ThesisService thesisService;
    private final JSONFormat jsonFormat;

    @Autowired
    public ThesisController(JSONFormat jsonFormat, ThesisService thesisService) {
        this.jsonFormat = jsonFormat;
        this.thesisService = thesisService;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new AddThesisValidator());
    }

    @PostMapping(value = "/api/thesis")
    public ResponseEntity<String> addThesis(@Valid @RequestBody AddThesis thesis, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        assert authentication != null;

        String username = (String) authentication.getPrincipal();
        if (thesisService.save(thesis, username)) {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/thesis/{thesisId}/inactive")
    public ResponseEntity<String> inactiveThesis(@PathVariable Long thesisId, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        assert authentication != null;

        String username = (String) authentication.getPrincipal();
        if (thesisService.makeInactive(thesisId, username)) {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Some error occurred", HttpStatus.BAD_REQUEST);
        }
    }
}

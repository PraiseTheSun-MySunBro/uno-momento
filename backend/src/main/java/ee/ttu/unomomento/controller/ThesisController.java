package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.model.Thesis;
import ee.ttu.unomomento.security.TokenAuthenticationService;
import ee.ttu.unomomento.service.ThesisService;
import ee.ttu.unomomento.validator.ThesisValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        binder.setValidator(new ThesisValidator());
    }

    @PostMapping(value = "/api/thesis")
    public HttpStatus postThesis(@Valid @RequestBody Thesis thesis, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        assert authentication != null;
        log.info(String.format("Post thesis: %s", thesis));
        thesisService.save(thesis, (String) authentication.getPrincipal());
        return HttpStatus.OK;
    }

}

package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.model.template.AccountPersonInformation;
import ee.ttu.unomomento.security.TokenAuthenticationService;
import ee.ttu.unomomento.service.AccountService;
import ee.ttu.unomomento.service.PersonService;
import ee.ttu.unomomento.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class PersonController {
    private static final int CURATORS_PER_PAGE = 5;

    private final JSONFormat jsonFormat;
    private final PersonService personService;
    private final AccountService accountService;

    @Autowired
    public PersonController(PersonService personService, JSONFormat jsonFormat, AccountService accountService) {
        this.personService = personService;
        this.jsonFormat = jsonFormat;
        this.accountService = accountService;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new PersonValidator());
    }

    @GetMapping(value = "/api/curators")
    public ResponseEntity<?> getCuratorsByPages(@RequestParam(required = false) Integer p, HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        if (authentication == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        log.info(String.format("Get Curators: %s", p != null ? "page = " + p.toString() : "All"));
        String username = authentication.getName();

        AccountPersonInformation accountPersonInformation = accountService.findAccountPersonInformationByUsername(username);

        Result<?> result = (p != null)
                ? personService.getAllCuratorsWithThesesByPages(--p, CURATORS_PER_PAGE)
                : personService.getAllCuratorsWithTheses(accountPersonInformation.getFacultyCode());

        return new ResponseEntity<>(result.formatJSON(jsonFormat), HttpStatus.OK);
    }
}

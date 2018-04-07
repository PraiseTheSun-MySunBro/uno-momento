package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.service.PersonService;
import ee.ttu.unomomento.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PersonController {
    private static final int CURATORS_PER_PAGE = 5;

    private final JSONFormat jsonFormat;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService, JSONFormat jsonFormat) {
        this.personService = personService;
        this.jsonFormat = jsonFormat;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new PersonValidator());
    }

    @GetMapping(value = "/api/curators")
    public ResponseEntity<?> getCuratorsByPages(@RequestParam(required = false) Integer p) {
        log.info(String.format("Get Curators: %s", p != null ? "page = " + p.toString() : "All"));
        Result<?> result = (p != null)
                ? personService.getAllCuratorsWithThesesByPages(--p, CURATORS_PER_PAGE)
                : personService.getAllCuratorsWithTheses();

        return new ResponseEntity<>(result.formatJSON(jsonFormat), HttpStatus.OK);
    }
}

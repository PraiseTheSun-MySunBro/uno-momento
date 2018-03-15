package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.service.PersonService;
import ee.ttu.unomomento.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PersonController {
    private static final int CURATORS_PER_PAGE = 5;

    @Autowired
    private JSONFormat jsonFormat;

    @Autowired
    private PersonService personService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new PersonValidator());
    }

    @GetMapping(value = "/curators")
    public String getCuratorsByPages(@RequestParam(required = false) Integer p) {
        log.info(String.format("Get Curators: %s", p != null ? "page = " + p.toString() : "All"));
        return ((p != null)
                    ? personService.getAllCuratorsWithThesesByPages(--p, CURATORS_PER_PAGE)
                    : personService.getAllCuratorsWithTheses())
                .formatJSON(jsonFormat);
    }

    @GetMapping(value = "/persons/{personId}")
    public String getPersonById(@PathVariable Long personId) {
        log.info(String.format("Get Person by ID: %d", personId));
        return personService.getPersonById(personId).formatJSON(jsonFormat);
    }

}

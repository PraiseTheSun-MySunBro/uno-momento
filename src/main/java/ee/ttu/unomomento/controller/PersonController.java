package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PersonController {
    private static final int CURATORS_PER_PAGE = 5;

    @Autowired
    private JSONFormat jsonFormat;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/curators")
    public String getCuratorsByPages(@RequestParam(required = false) Integer p) {
        return ((p != null)
                    ? personService.getAllCuratorsWithThesesByPages(--p, CURATORS_PER_PAGE)
                    : personService.getAllCuratorsWithTheses())
                .formatJSON(jsonFormat);
    }

    @GetMapping(value = "/persons/{personId}")
    public String getPersonById(@PathVariable Long personId) {
        return personService.getPersonById(personId).formatJSON(jsonFormat);
    }

}

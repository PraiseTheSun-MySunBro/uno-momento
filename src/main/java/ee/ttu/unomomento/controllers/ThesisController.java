package ee.ttu.unomomento.controllers;

import ee.ttu.unomomento.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ThesisController {
    private static final int CURATORS_PAGE_SIZE = 1;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/curators")
    public String getCurators() {
        JSONFormat jsonFormat = new JSONFormat().recordFormat(JSONFormat.RecordFormat.OBJECT).header(false);
        return personService.getCurators().formatJSON(jsonFormat);
    }

}

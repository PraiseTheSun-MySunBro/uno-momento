package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.db.tables.pojos.Thesis;
import ee.ttu.unomomento.service.PersonService;
import ee.ttu.unomomento.validator.ThesisValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ThesisController {

    @Autowired
    private PersonService personService;

    @Autowired
    private JSONFormat jsonFormat;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new ThesisValidator());
    }

    @PostMapping(value = "/thesis")
    public HttpStatus postThesis(@Valid @RequestBody Thesis thesis) {
        log.info(String.format("Post thesis: %s", thesis));
        System.out.println(thesis);
        return HttpStatus.OK;
    }

}

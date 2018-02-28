package ee.ttu.unomomento.controllers;

import com.google.gson.Gson;
import ee.ttu.unomomento.services.PersonService;
import ee.ttu.unomomento.services.ThesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ThesisController {
    private static final int CURATORS_PAGE_SIZE = 1;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private PersonService personService;

    @Autowired
    private Gson gson;


    @GetMapping(value = "/curators/page/{pageNumber}")
    public String getCuratorsOnPage(@PathVariable Integer pageNumber) {
        return gson.toJson(personService.getCurators(pageNumber, CURATORS_PAGE_SIZE));
    }

    @GetMapping(value = "/curators")
    public String getCurators() {
        return gson.toJson(personService.getCurators());
    }

    @GetMapping(value = "/theses/curator/{curatorId}")
    public String getThesesByCurator(@PathVariable Long curatorId) {
        return gson.toJson(thesisService.getTheses());
    }

}

package ee.ttu.unomomento.controllers;

import com.google.gson.Gson;
import ee.ttu.unomomento.models.Person;
import ee.ttu.unomomento.models.Thesis;
import ee.ttu.unomomento.models.ThesisOwner;
import ee.ttu.unomomento.services.PersonService;
import ee.ttu.unomomento.services.ThesisOwnerService;
import ee.ttu.unomomento.services.ThesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ThesisController {
    private static final int CURATORS_PAGE_SIZE = 1;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ThesisOwnerService thesisOwnerService;

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

    @PostMapping(value = "/curator/{personId}/thesis")
    public String postThesis(@RequestBody Thesis thesis, @PathVariable Long personId) {
        /**
          Post JSON with values from Thesis class
         */
        thesis.setThesisStateCode((short) 1);
        thesisService.insert(thesis);
        Person person = personService.getById(personId);
        ThesisOwner thesisOwner = new ThesisOwner();
        thesisOwner.setPersonId(personId);
        thesisOwner.setThesisId(thesis.getThesisId());
        thesisOwner.setRoleCode((short) 2);
        thesisOwnerService.insert(thesisOwner);
        System.out.println(thesis);
        return "Ok";
    }

    @PostMapping(value = "/student/{personId}/thesis")
    public String postStudentThesis(@RequestBody Thesis thesis, @PathVariable Long personId) {
        /**
         Post JSON with values from Thesis class
         */
        thesis.setThesisStateCode((short) 1);
        thesisService.insert(thesis);
        Person person = personService.getById(personId);
        ThesisOwner thesisOwner = new ThesisOwner();
        thesisOwner.setPersonId(personId);
        thesisOwner.setThesisId(thesis.getThesisId());
        thesisOwner.setRoleCode((short) 1);
        thesisOwnerService.insert(thesisOwner);
        System.out.println(thesis);
        return "Ok";
    }

    @GetMapping(value = "/persons/{personId}")
    public String getPersonsById(@PathVariable Long personId) {
        return gson.toJson(personService.getById(personId));
    }
}

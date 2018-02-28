package ee.ttu.unomomento.controllers;

import com.google.gson.Gson;
import ee.ttu.unomomento.configurations.LocaleConfig;
import ee.ttu.unomomento.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ResourceBundle;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private ResourceBundle bundle;

    @Autowired
    private Gson gson;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/")
    public String index() {
        log.info("==========GET Persons==========\n{}", gson.toJson(personService.getPersons()));
        return "index";
    }
}

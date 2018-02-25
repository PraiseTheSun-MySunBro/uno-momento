package ee.ttu.unomomento.controllers;

import ee.ttu.unomomento.configurations.Config;
import ee.ttu.unomomento.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private Config config;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/")
    public String index() {
        System.out.println(personService.getPersons());
        return "index";
    }
}

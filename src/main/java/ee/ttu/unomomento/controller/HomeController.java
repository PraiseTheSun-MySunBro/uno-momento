package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.ResourceBundle;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private ResourceBundle bundle;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new PersonValidator());
    }

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }
}

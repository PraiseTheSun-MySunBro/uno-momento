package ee.ttu.unomomento.controller;

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

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }
}

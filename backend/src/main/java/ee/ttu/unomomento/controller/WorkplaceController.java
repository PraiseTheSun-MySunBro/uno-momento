package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.model.Thesis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/workplace/")
public class WorkplaceController {

    @PostMapping("submit")
    public void submit(Thesis thesis) {

    }
}

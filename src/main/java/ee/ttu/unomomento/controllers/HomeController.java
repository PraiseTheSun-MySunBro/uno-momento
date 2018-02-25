package ee.ttu.unomomento.controllers;

import ee.ttu.unomomento.configurations.Config;
import ee.ttu.unomomento.models.Person;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.jooq.impl.DSL.table;

@Controller
public class HomeController {
    @Autowired
    private Config config;

    @Autowired
    private DSLContext connection;

    @GetMapping(value = "/")
    public String index() {
        List<Person> result = this.connection.select().from("person").fetch().into(Person.class);
        for (Person r : result) {
            System.out.println(r);
        }
        return "index";
    }
}

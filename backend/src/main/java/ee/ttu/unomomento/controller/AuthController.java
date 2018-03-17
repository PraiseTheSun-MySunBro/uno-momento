package ee.ttu.unomomento.controller;

import com.google.gson.Gson;
import ee.ttu.unomomento.model.Account;
import ee.ttu.unomomento.service.AccountService;
import ee.ttu.unomomento.validator.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONFormat;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    private final AccountService accountService;
    private final JSONFormat jsonFormat;
    private final Gson gson;


    @Autowired
    public AuthController(AccountService accountService, JSONFormat jsonFormat, Gson gson) {
        this.accountService = accountService;
        this.jsonFormat = jsonFormat;
        this.gson = gson;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new AccountValidator());
    }

    @PostMapping(value = "/auth/register", produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody Account account) {
        try {
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Some error occured " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/auth/user/{accountUsername}")
    public String getAccountByUsername(@PathVariable(value = "accountUsername") String username) {
        return gson.toJson(accountService
                .findAccountByUsername(username));
    }
}

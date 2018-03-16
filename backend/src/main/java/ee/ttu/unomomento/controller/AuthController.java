package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.db.tables.pojos.Account;
import ee.ttu.unomomento.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class AuthController {

    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/auth/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Some error occured " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/auth/user")
    public HttpStatus wat() {
        return HttpStatus.OK;
    }
}

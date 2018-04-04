package ee.ttu.unomomento.controller;

import com.google.gson.Gson;
import ee.ttu.unomomento.model.template.AccountPersonInformation;
import ee.ttu.unomomento.model.template.UserRegistration;
import ee.ttu.unomomento.security.TokenAuthenticationService;
import ee.ttu.unomomento.service.AccountService;
import ee.ttu.unomomento.validator.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    private final AccountService accountService;
    private final Gson gson;

    @Autowired
    public AuthController(AccountService accountService, Gson gson) {
        this.accountService = accountService;
        this.gson = gson;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegistrationValidator());
    }

    @PostMapping(value = "/auth/register", produces = "application/json")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistration userRegistration) {
        log.info(String.format("[REQUEST]: User %s requested registration", userRegistration.getUsername()));
        try {
            accountService.saveAccount(userRegistration);
            log.info(String.format("[RESULT]: Successfully registered user %s", userRegistration.getUsername()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            log.info(String.format("[RESULT]: Some error with user %s registration", userRegistration.getUsername()));
            return new ResponseEntity<>("Some error occured " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/auth/user", produces = "application/json")
    public String getAccount(HttpServletRequest httpRequest) {
        Authentication authentication = TokenAuthenticationService.getAuthentication(httpRequest);
        assert authentication != null;

        String username = (String) authentication.getPrincipal();
        log.info(String.format("[REQUEST]: User %s requested user information", username));

        AccountPersonInformation accountPersonInformation = accountService
                .findAccountPersonInformationByUsername(username);
        assert accountPersonInformation != null;

        return gson.toJson(accountPersonInformation);
    }
}

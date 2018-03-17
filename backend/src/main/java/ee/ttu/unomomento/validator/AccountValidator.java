package ee.ttu.unomomento.validator;

import ee.ttu.unomomento.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");

        Account account = (Account) target;
        if (account.getEmail().length() > 256) {
            errors.rejectValue("email", "email.too.long");
        }
        if (account.getUsername().length() > 32) {
            errors.rejectValue("username", "username.too.long");
        }
        if (account.getPassword().length() > 72) {
            errors.rejectValue("password", "password.too.long");
        }
        if (account.getUsername().length() < 3) {
            errors.rejectValue("username", "username.too.short");
        }
        if (account.getPassword().length() < 6) {
            errors.rejectValue("password", "password.too.short");
        }

    }
}

package ee.ttu.unomomento.validator;

import ee.ttu.unomomento.model.template.UserRegistration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistration.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uniId", "uniId.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleCode", "roleCode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degreeCode", "degreeCode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facultyCode", "facultyCode.empty");

        UserRegistration account = (UserRegistration) target;
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
        if (account.getFirstname().length() > 1000) {
            errors.rejectValue("firstname", "firstname.too.long");
        }
        if (account.getLastname().length() > 1000) {
            errors.rejectValue("lastname", "lastname.too.long");
        }
        if (account.getUniId().length() != 6) {
            errors.rejectValue("uniId", "uniId.length.is.not.6");
        }
        // TODO: regex
    }
}

package ee.ttu.unomomento.validator;

import ee.ttu.unomomento.model.Thesis;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ThesisValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Thesis.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "eeTitle", "eeTitle.name");
    }
}

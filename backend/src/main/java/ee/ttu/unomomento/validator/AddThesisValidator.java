package ee.ttu.unomomento.validator;

import ee.ttu.unomomento.model.template.AddThesis;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

public class AddThesisValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddThesis.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degreeCode", "degreeCode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facultyCode", "facultyCode.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleCode", "roleCode.empty");

        AddThesis addThesis = (AddThesis) target;

        if (Objects.equals(addThesis.getEeTitle().trim(), "") && Objects.equals(addThesis.getEeDescription().trim(), "") &&
            Objects.equals(addThesis.getEnTitle().trim(), "") && Objects.equals(addThesis.getEnDescription().trim(), "")) {
            errors.reject("one.or.more.languages.must.be.filled");
        }
        if (!Objects.equals(addThesis.getEeTitle().trim(), "") && Objects.equals(addThesis.getEeDescription().trim(), "") ||
            Objects.equals(addThesis.getEeTitle().trim(), "") && !Objects.equals(addThesis.getEeDescription().trim(), "")) {
            errors.reject("eeTitle.eeDescription.must.be.filled");
        }
        if (!Objects.equals(addThesis.getEnTitle().trim(), "") && Objects.equals(addThesis.getEnDescription().trim(), "") ||
                Objects.equals(addThesis.getEnTitle().trim(), "") && !Objects.equals(addThesis.getEnDescription().trim(), "")) {
            errors.reject("enTitle.enDescription.must.be.filled");
        }

        if (addThesis.getTags() != null && addThesis.getTags().size() > 5) {
            errors.reject("thesisTags.max.amount.could.be.5");
        }

    }
}

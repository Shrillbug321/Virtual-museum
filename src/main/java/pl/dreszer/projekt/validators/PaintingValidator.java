package pl.dreszer.projekt.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.dreszer.projekt.models.Painting;

public class PaintingValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Painting.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "value", "Negative.painting.value");
        Painting painting = (Painting) target;
        if (painting.getAuthor().equals(painting.getName()))
        {
            errors.rejectValue("author", "Error.painting.equalsAuthorAndName");
        }
    }
}

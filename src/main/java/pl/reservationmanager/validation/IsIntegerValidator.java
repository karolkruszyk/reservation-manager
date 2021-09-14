package pl.reservationmanager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsIntegerValidator implements ConstraintValidator<IsInteger, String> {
    @Override
    public void initialize(IsInteger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;
        if(value != null) {
            try {
                Integer.parseInt(value);
                result = true;
            } catch (Exception e) {
                result = false;
            }
        }
        return result;
    }
}

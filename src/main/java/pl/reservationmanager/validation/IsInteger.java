package pl.reservationmanager.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsIntegerValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsInteger {

    public String value();

    public String message() default "must be an integer";
    public Class<?>[] groups() default {};
    public Class <? extends Payload>[] payload() default {};
}

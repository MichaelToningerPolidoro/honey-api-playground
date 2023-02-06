package com.honeyautomation.apiplayground.annotation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDate {

    String message() default "The date is not valid. It must be in pattern yyyy-mm-dd and it needs to be in the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

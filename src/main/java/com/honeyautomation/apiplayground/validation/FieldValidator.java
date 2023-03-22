package com.honeyautomation.apiplayground.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class FieldValidator {

    private FieldValidator() {}

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validate(Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }
}

package com.honey.apiplayground.annotation.validation;

import com.honey.apiplayground.creator.LocalDateCreator;
import com.honey.apiplayground.utils.PatternValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public boolean isValid(String rawDate, ConstraintValidatorContext constraintValidatorContext) {
        if (!PatternValidation.isDateValid(rawDate)) {
            return false;
        }

        try {
            final LocalDate date = LocalDateCreator.getLocalDate(rawDate);
            final LocalDate today = LocalDateCreator.getToday();

            if (date.isEqual(today) || date.isAfter(today)) {
                return false;
            }

        } catch (Exception ignored) {
            return false;
        }

        return true;
    }
}

package com.honeyautomation.apiplayground.annotation.validation;

import com.honeyautomation.apiplayground.factory.LocalDateFactory;
import com.honeyautomation.apiplayground.utils.PatternValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public boolean isValid(String rawDate, ConstraintValidatorContext constraintValidatorContext) {
        if (!PatternValidationUtils.isDateValid(rawDate)) {
            return false;
        }

        try {
            final LocalDate date = LocalDateFactory.getLocalDate(rawDate);
            final LocalDate today = LocalDateFactory.getToday();

            if (date.isEqual(today) || date.isAfter(today)) {
                return false;
            }

        } catch (Exception ignored) {
            return false;
        }

        return true;
    }
}

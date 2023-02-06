package com.honeyautomation.apiplayground.annotation.validation;

import com.honeyautomation.apiplayground.constants.General;
import com.honeyautomation.apiplayground.constants.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public boolean isValid(String rawDate, ConstraintValidatorContext constraintValidatorContext) {
        if (!Pattern.matches(Validations.DATE_VALIDATION_REGEX_PATTERN, rawDate)) {
            return false;
        }

        try {
            // FIXME add a factory to abstract this creation
            final String[] splitRawDate = rawDate.split(General.DATE_SEPARATOR);
            final LocalDate today = LocalDate.now(General.STANDARD_ZONE_ID);
            final LocalDate date = LocalDate.of(parseInt(splitRawDate[0]), parseInt(splitRawDate[1]), parseInt(splitRawDate[2]))
                    .atStartOfDay(General.STANDARD_ZONE_ID)
                    .toLocalDate();

            if (date.isEqual(today) || date.isAfter(today)) {
                return false;
            }

        } catch (Exception ignored) {
            return false;
        }

        return true;
    }
}

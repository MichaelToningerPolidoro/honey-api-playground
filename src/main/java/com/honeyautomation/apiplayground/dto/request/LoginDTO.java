package com.honeyautomation.apiplayground.dto.request;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.constants.ValidationsMessages;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Builder
public class LoginDTO implements Serializable {

    @NotBlank(message = ValidationsMessages.NOT_BLANK_EMAIL)
    @Size(max = Validations.MAX_LENGTH_EMAIL, message = ValidationsMessages.MAX_LENGTH_EMAIL)
    @Email(regexp = Validations.REGEX_PATTERN_EMAIL_VALIDATION, message = ValidationsMessages.REGEX_PATTERN_EMAIL_VALIDATION)
    private String email;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_PASSWORD)
    @Size(max = Validations.MAX_LENGTH_PASSWORD, message = ValidationsMessages.MAX_LENGTH_PASSWORD)
    private String password;
}

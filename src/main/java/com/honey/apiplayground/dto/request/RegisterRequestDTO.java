package com.honey.apiplayground.dto.request;

import com.honey.apiplayground.constants.Validations;
import com.honey.apiplayground.constants.ValidationsMessages;
import com.honey.apiplayground.dto.model.BornDataDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class RegisterRequestDTO implements Serializable {

    @NotBlank(message = ValidationsMessages.NOT_BLANK_NICK_NAME)
    @Size(max = Validations.MAX_LENGTH_NICK_NAME, message = ValidationsMessages.MAX_LENGTH_NICK_NAME)
    @Pattern(regexp = Validations.REGEX_PATTERN_NICKNAME_VALIDATION, message = ValidationsMessages.REGEX_PATTERN_NICKNAME_VALIDATION)
    private String nickName;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_NAME)
    @Size(max = Validations.MAX_LENGTH_NAME, message = ValidationsMessages.MAX_LENGTH_NAME)
    @Pattern(regexp = Validations.REGEX_PATTERN_NAME_VALIDATION, message = ValidationsMessages.REGEX_PATTERN_NAME_VALIDATION)
    private String name;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_EMAIL)
    @Size(max = Validations.MAX_LENGTH_EMAIL, message = ValidationsMessages.MAX_LENGTH_EMAIL)
    @Email(regexp = Validations.REGEX_PATTERN_EMAIL_VALIDATION, message = ValidationsMessages.REGEX_PATTERN_EMAIL_VALIDATION)
    private String email;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_PASSWORD)
    @Size(max = Validations.MAX_LENGTH_PASSWORD, message = ValidationsMessages.MAX_LENGTH_PASSWORD)
    @Pattern(regexp = Validations.REGEX_PATTERN_PASSWORD_VALIDATION, message = ValidationsMessages.REGEX_PATTERN_PASSWORD_VALIDATION)
    private String password;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_PROGRAMMING_TIME)
    @Size(max = Validations.MAX_LENGTH_PROGRAMMING_TIME, message = ValidationsMessages.MAX_LENGTH_PROGRAMMING_TIME)
    private String programmingTime;

    @NotNull(message = ValidationsMessages.NOT_NULL_BORN_DATA)
    @Valid
    private BornDataDTO bornData;

    @NotEmpty(message = ValidationsMessages.NOT_NULL_HOBBIES_LIST)
    @Size(max = Validations.MAX_LENGTH_HOBBIES_LIST, message = ValidationsMessages.MAX_LENGTH_HOBBIES_LIST)
    private List<String> hobbies;
}

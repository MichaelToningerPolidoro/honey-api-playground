package com.honeyautomation.apiplayground.dto.request;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
public class RegisterRequestDTO implements Serializable {

    @NotBlank(message = "The nickName cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_NICK_NAME, message = "The character limit for nickName is 32")
    private String nickName;

    @NotBlank(message = "The name cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_NAME, message = "The character limit for name is 64")
    private String name;

    @NotBlank(message = "The email cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_EMAIL, message = "The character limit for email is 64")
    @Email(regexp = Validations.REGEX_PATTERN_EMAIL_VALIDATION, message = "The email needs to be valid")
    private String email;

    @NotBlank(message = "The password cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_PASSWORD, message = "The character limit for password is 64")
    @Pattern(regexp = Validations.REGEX_PATTERN_PASSWORD_VALIDATION, message = "The password can only contain numbers, letters and these characters !\";#$%&'()*+,-/:<=>?@[]_{|}.")
    private String password;

    @NotBlank(message = "The programmingTime cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_PROGRAMMING_TIME, message = "The character limit for programmingTime is 256")
    private String programmingTime;

    @NotNull(message = "The bornData cannot be null")
    @Valid
    private BornDataDTO bornData;

    @NotEmpty(message = "The hobbies cannot be null or empty")
    @Size(max = Validations.MAX_LENGTH_HOBBIES_LIST, message = "The max size os hobbies list is 3")
    private List<String> hobbies;
}

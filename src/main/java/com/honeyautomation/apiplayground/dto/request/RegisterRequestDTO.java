package com.honeyautomation.apiplayground.dto.request;

import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
public class RegisterRequestDTO {

    @NotBlank(message = "The nickName cannot be null or blank")
    @Size(max = 32, message = "The character limit for nickName is 32")
    private String nickName;

    @NotBlank(message = "The name cannot be null or blank")
    @Size(max = 64, message = "The character limit for name is 64")
    private String name;

    @NotBlank(message = "The email cannot be null or blank")
    @Size(max = 64, message = "The character limit for email is 64")
    @Email(message = "The email needs to be valid", regexp = Validations.EMAIL_VALIDATION_REGEX_PATTERN)
    private String email;

    @NotBlank(message = "The password cannot be null or blank")
    @Size(max = 64, message = "The character limit for password is 64")
    private String password;

    @NotBlank(message = "The programmingTime cannot be null or blank")
    @Size(max = 256, message = "The character limit for programmingTime is 256")
    private String programmingTime;

    @NotNull(message = "The bornData cannot be null")
    @Valid
    private BornDataDTO bornData;

    @NotEmpty(message = "The hobbies cannot be null or empty")
    @Size(max = 3, message = "The max size os hobbies list is 3")
    private List<String> hobbies;

}

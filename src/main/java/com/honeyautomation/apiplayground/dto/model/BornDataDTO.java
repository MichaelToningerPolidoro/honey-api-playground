package com.honeyautomation.apiplayground.dto.model;

import com.honeyautomation.apiplayground.annotation.validation.ValidDate;
import com.honeyautomation.apiplayground.constants.Validations;
import com.honeyautomation.apiplayground.constants.ValidationsMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class BornDataDTO implements Serializable {

    @NotBlank(message = ValidationsMessages.NOT_BLANK_DATE)
    @Size(max = Validations.MAX_LENGTH_BORN_DATA_DATE, message = ValidationsMessages.MAX_LENGTH_BORN_DATA_DATE)
    @ValidDate
    private String date;

    @NotBlank(message = ValidationsMessages.NOT_BLANK_COUNTRY)
    @Size(max = Validations.MAX_LENGTH_COUNTRY, message = ValidationsMessages.MAX_LENGTH_COUNTRY)
    private String country;

}

package com.honeyautomation.apiplayground.dto.model;

import com.honeyautomation.apiplayground.annotation.validation.ValidDate;
import com.honeyautomation.apiplayground.constants.Validations;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
public class BornDataDTO implements Serializable {

    @NotBlank(message = "The date cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_BORN_DATA_DATE, message = "The character limit for date is 64")
    @ValidDate
    private String date;

    @NotBlank(message = "The country cannot be null or blank")
    @Size(max = Validations.MAX_LENGTH_COUNTRY, message = "The character limit for country is 64")
    private String country;

}

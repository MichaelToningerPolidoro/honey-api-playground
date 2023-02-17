package com.honeyautomation.apiplayground.dto.model;

import com.honeyautomation.apiplayground.annotation.validation.ValidDate;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
public class BornDataDTO implements Serializable {

    @NotBlank(message = "The date cannot be null or blank")
    @Size(max = 10, message = "The character limit for date is 64")
    @ValidDate
    private String date;

    @NotBlank(message = "The country cannot be null or blank")
    @Size(max = 48, message = "The character limit for country is 64")
    private String country;

}

package com.honeyautomation.apiplayground.dto.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class BornDataDTO {

    @NotBlank(message = "The date cannot be null or blank")
    @Size(max = 10, message = "The character limit for date is 64")
    // FIXME Find some way to validate this via method
    //  see if make sense add @Pattern to pre-validate the structure yyyy-mm-dd

    private String date;

    @NotBlank(message = "The country cannot be null or blank")
    @Size(max = 48, message = "The character limit for country is 64")
    private String country;

}

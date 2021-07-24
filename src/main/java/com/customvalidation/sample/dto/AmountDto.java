package com.customvalidation.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountDto {

    @Digits(integer = 10, fraction = 2, message = "- can accept only decimal values with max length 10 and two decimals")
    //@Pattern(regexp = "^\\d+\\.\\d\\d$", message = " must be in 00.00 format") //TODO : Uncomment when LCE is ready
    public String value;

}
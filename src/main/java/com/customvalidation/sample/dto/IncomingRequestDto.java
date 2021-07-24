package com.customvalidation.sample.dto;

import com.customvalidation.sample.validation.NotNullIfAnotherFieldHasValue;
import com.customvalidation.sample.validation.NotNullIfAnotherFieldHasValueForTwoFieldValidations;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = IncomingRequestDto.class)
@NotNullIfAnotherFieldHasValue.List({

        @NotNullIfAnotherFieldHasValue(
                fieldName = "transactionType",
                fieldValue = "Sale",
                dependFieldName = "amountDto.value",
                message = " - amount is mandatory for Sale requests"),

        @NotNullIfAnotherFieldHasValue(
                fieldName = "transactionType",
                fieldValue = "VoidSale",
                dependFieldName = "reversalType",
                message = " - Reversal Type is mandatory for VoidSale requests"),


})

@NotNullIfAnotherFieldHasValueForTwoFieldValidations.List({
        @NotNullIfAnotherFieldHasValueForTwoFieldValidations(
                fieldName1 = "transactionType",
                fieldValue1 = "VoidSale",
                fieldName2 = "reversalType",
                fieldValue2 = "Cancelled",
                dependFieldName = "reversalId",
                message =  "- reversalId is mandatory for Cancelled transactions"),
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomingRequestDto {

    @NotNull
    public TransactionType transactionType;

    public ReversalType reversalType;

    public String reversalId;

    public AmountDto amountDto;


}

package com.customvalidation.sample.controller;

import com.customvalidation.sample.dto.IncomingRequestDto;
import com.customvalidation.sample.dto.StandardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ChargeController {

    @RequestMapping(value="v1/customvalidations",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public StandardDto chargeEntity(@RequestBody @Valid IncomingRequestDto incomingRequestDto)
    {
        return StandardDto.builder()
                .status("Charged")
                .build();
    }
}

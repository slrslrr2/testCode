package com.test.code.organize;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public ResponseEntity<?> test(@Validated TestRequestDTO testRequestDTO, BindingResult bindingResult) {
        System.out.println("bindingResult = " + bindingResult);

        ErrorDTO.ErrorDTOBuilder errorMsd = ErrorDTO.builder().errorCode("401").errorMsg("errorMsd");

        return new ResponseEntity<Object>(errorMsd, HttpStatus.BAD_REQUEST);
    }
}

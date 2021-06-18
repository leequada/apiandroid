package com.namph.security.exception;


import com.namph.security.dto.ResponseData;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
//    @ExceptionHandler(CustomGlobalException.class)
//    public ResponseEntity<ResponseData> handleBadRequestException(CustomGlobalException ex) {
//        log.info("Bad request exception");
//        return ResponseEntity.ok(ResponseData.ofFail(ex.getMessage()));
//    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        HashMap<String, String> fieldErrors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        ResponseData responseData = new ResponseData();
        responseData.setMessage("data.invalid");
        responseData.setData(fieldErrors);
        responseData.setErrorCode("2");
        return ResponseEntity.ok(responseData);


    }
}

package com.developerscambodia.devcoursesservice.exception;


import com.developerscambodia.devcoursesservice.base.BaseApi;
import com.developerscambodia.devcoursesservice.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handlerServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timeStamp(LocalDateTime.now())
                .messages("Something Went Wrong Please Check...!")
                .errors(e.getReason())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException e) {

        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError error : e.getFieldErrors()) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("name", error.getField());
            errorDetails.put("message", error.getDefaultMessage());
            errors.add(errorDetails);
        }

        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .messages("Validation Is Error, Please Check Detail Messages!!!")
                .errors(errors)
                .build();
    }


}

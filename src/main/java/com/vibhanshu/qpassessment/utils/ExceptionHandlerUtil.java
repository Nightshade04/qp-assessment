package com.vibhanshu.qpassessment.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerUtil {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public Map<String, String> nullPointerExceptionHandler(NullPointerException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "The resource you are looking for in the DB, does not exists!\n" + ex.getLocalizedMessage());

        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> noSuchElementExceptionHandler(NoSuchElementException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "The resource with given Id in the request, does not exist in DB!\n" + ex.getLocalizedMessage());

        return response;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request you sent is not accepted by the server!\n" + ex.getLocalizedMessage());

        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> generalExceptionHandler(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Some error occurred in the server. Please check after some time.\n" + ex.getLocalizedMessage());

        return response;
    }

}

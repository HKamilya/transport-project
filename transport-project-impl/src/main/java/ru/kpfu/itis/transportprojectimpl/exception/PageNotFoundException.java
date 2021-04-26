package ru.kpfu.itis.transportprojectimpl.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends Exception {
    public PageNotFoundException(String message){
        super(message);
    }
}

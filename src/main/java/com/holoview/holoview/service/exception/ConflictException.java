package com.holoview.holoview.service.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.CONFLICT)
@NoArgsConstructor
@AllArgsConstructor
public class ConflictException extends RuntimeException{
    public List<String> fields;

    @Override
    public String getMessage() {
        return "Conflicted fields: " + this.fields.toString();
    }
}
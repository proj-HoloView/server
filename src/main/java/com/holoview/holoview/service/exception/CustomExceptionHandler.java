package com.holoview.holoview.service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BlobStorageUploadException.class)
    public void blobStorageHandler(BlobStorageUploadException e) {
        System.out.println("Blob storage file upload exception: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundHandler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> conclictHandler() {
        return ResponseEntity.status(409).build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestHandler(BadRequestException e) {
        return ResponseEntity.badRequest().body(new OutError(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerErrorHandler(Exception e) {
        System.out.println(e.toString());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    public record OutError(String message) { };
}
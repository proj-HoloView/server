package com.holoview.holoview.service.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlobStorageUploadException extends RuntimeException {
    public String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
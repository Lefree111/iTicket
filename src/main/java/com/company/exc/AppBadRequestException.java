package com.company.exc;

import org.springframework.remoting.RemoteTimeoutException;

public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(String message) {
        super(message);
    }
}

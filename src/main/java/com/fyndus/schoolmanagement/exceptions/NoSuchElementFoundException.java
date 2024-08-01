package com.fyndus.schoolmanagement.exceptions;

import com.fyndus.schoolmanagement.util.ResponseMessage;

public class NoSuchElementFoundException extends RuntimeException{
    public NoSuchElementFoundException() {
        super(ResponseMessage.EMPTY);
    }
}

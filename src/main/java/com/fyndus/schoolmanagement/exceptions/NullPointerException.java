package com.fyndus.schoolmanagement.exceptions;

import com.fyndus.schoolmanagement.util.ResponseMessage;

public class NullPointerException extends RuntimeException{
    public NullPointerException() {
        super(ResponseMessage.NOT_FOUND);
    }

}

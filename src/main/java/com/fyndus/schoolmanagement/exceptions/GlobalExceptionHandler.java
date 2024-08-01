package com.fyndus.schoolmanagement.exceptions;

import com.fyndus.schoolmanagement.DTO.ResponseDTO;
import com.fyndus.schoolmanagement.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDTO> nullPointerExceptionHandler(NullPointerException e) {
        final ResponseDTO responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    public ResponseEntity<ResponseDTO> noSuchElementFoundExceptionHandler(NoSuchElementFoundException e) {
        final ResponseDTO responseDTO = ResponseDTO.builder().message(e.getMessage()).build();
        return new ResponseEntity<>(responseDTO, HttpStatus.NO_CONTENT);
    }
}

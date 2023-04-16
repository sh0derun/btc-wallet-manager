package com.shaderun.exception;

import com.shaderun.api.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiWalletExceptionHandler {

    @ExceptionHandler(ApiWalletException.class)
    public ResponseEntity<Result> exception(ApiWalletException exception){
        HttpStatus conflict = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new Result(conflict, exception.getMessage()), conflict);
    }

}

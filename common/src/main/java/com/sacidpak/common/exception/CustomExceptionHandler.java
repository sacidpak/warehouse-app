package com.sacidpak.common.exception;

import com.sacidpak.common.dto.ErrorHandlerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<ErrorHandlerDTO> handleAllExceptions(BusinessException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ex.printStackTrace();

        if (ex.getValidation() != null && ex.getValidation().getCode() != null) {
            String respErrCode = ex.getValidation().getCode();

            ErrorHandlerDTO response = new ErrorHandlerDTO(respErrCode, respErrCode);
            return new ResponseEntity<>(response, httpStatus);
        } else {
            ErrorHandlerDTO response = new ErrorHandlerDTO( String.valueOf(httpStatus.value()), ex.getMessage());
            return new ResponseEntity<>(response, httpStatus);
        }
    }
}

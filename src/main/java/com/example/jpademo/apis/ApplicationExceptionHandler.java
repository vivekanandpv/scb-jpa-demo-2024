package com.example.jpademo.apis;

import com.example.jpademo.exceptions.GeneralAuthenticationException;
import com.example.jpademo.exceptions.LoginFailedException;
import com.example.jpademo.exceptions.RecordNotFoundException;
import com.example.jpademo.utils.AppUtils;
import com.example.jpademo.viewmodels.ValidationErrorPageViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {
    private final Logger logger;

    public ApplicationExceptionHandler() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @ExceptionHandler(GeneralAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleGeneralAuthenticationException(GeneralAuthenticationException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Authentication failed"));
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<Map<String, String>> handleLoginFailedException(LoginFailedException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Could not process login"));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorPageViewModel> handleValidationException(MethodArgumentNotValidException exception) {
        logger.warn("validation", exception);
        return ResponseEntity.badRequest().body(AppUtils.getValidationErrorPage(exception, exception.getBindingResult().getTarget().getClass()));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException exception) {
        logger.warn(exception.getMessage());
        return ResponseEntity.status(404).body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleOtherExceptions(RuntimeException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.badRequest().build();
    }
}

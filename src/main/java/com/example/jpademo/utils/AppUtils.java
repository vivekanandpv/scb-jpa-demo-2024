package com.example.jpademo.utils;

import com.example.jpademo.viewmodels.DomainErrorViewModel;
import com.example.jpademo.viewmodels.ValidationErrorPageViewModel;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppUtils {
    public static ValidationErrorPageViewModel getValidationErrorPage(MethodArgumentNotValidException exception, Class<?> modelClass) {
        List<DomainErrorViewModel> domainErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new DomainErrorViewModel(fe.getField(), fe.getDefaultMessage()))
                .sorted(Comparator.comparing(DomainErrorViewModel::getField))
                .collect(Collectors.toList());


        return new ValidationErrorPageViewModel(
                modelClass.getName(),
                LocalDateTime.now(),
                domainErrors
        );
    }
}

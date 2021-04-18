package com.mylayui.insurance.framework.mvc;

import com.mylayui.insurance.framework.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handle(RuntimeException exception) {
        exception.printStackTrace();
        return exception.getMessage();

    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public String handle(MyException exception) {
        exception.printStackTrace();
        return exception.getMessage();

    }
}

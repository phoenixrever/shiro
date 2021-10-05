package com.phoenixhell.lession1.excption;

import com.phoenixhell.lession1.util.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:57
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public R globalError(MyException e) {
        e.printStackTrace();
        return R.error(e.getCode(), e.getMsg());
    }
}

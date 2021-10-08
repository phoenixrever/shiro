package com.phoenixhell.shiroToken.excption;


import com.phoenixhell.shiroToken.util.R;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
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

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public R authorization(AuthorizationException e) {
        System.out.println(e.getMessage());
        return R.error(20001,"权限认证失败");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public R unauthorized(UnauthorizedException e) {
        System.out.println(e.getMessage());
        return R.error(20002,"无此权限");
    }
}

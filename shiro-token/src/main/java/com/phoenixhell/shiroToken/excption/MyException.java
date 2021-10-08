package com.phoenixhell.shiroToken.excption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author phoenixhell
 * @since 2021/10/4 0004-上午 9:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    private Integer code; //状态码
    private String msg;//异常信息
}

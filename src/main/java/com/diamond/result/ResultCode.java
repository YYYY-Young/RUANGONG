package com.diamond.result;

/**
 * @Classname ResultCode
 * @Description TODO
 * @Date 2020/8/10 15:34
 * @Created by lrf
 */
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}

package com.api.core.response;


/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    public static Result genSuccessResult() {
        return new Result(ResultEnum.SUCCESS_MESSAGE,null);
    }

    public static Result genSuccessResult(Object data) {
        return new Result(ResultEnum.SUCCESS_MESSAGE,data);
    }
    public static Result genSuccessResult(Object data, String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    public static Result genCreatedSuccessResult() {
        return new Result(ResultEnum.CREATED);
    }

    public static Result genCreatedSuccessResult(Object data) {
        return new Result(ResultEnum.CREATED,data);
    }

    public static Result genDeleteSuccessResult() {
        return new Result(ResultEnum.DELETED);
    }

    public static Result genUploadSuccessResult() {
        return new Result(ResultEnum.UPLOADED);
    }

    public static Result genFailResult() {
        return new Result(ResultEnum.FAIL);
    }

    public static Result genFailResult(Object data) {
        return new Result(ResultEnum.FAIL,data);
    }

    public static Result genUnauthorizedResult() {
        return new Result(ResultEnum.UNAUTHORIZED);
    }

    public static Result genForbiddenResult() {
        return new Result(ResultEnum.FORBIDDEN);
    }

    public static Result genExceptionResult() {
        return new Result(ResultEnum.INTERNAL_SERVER_ERROR);
    }

    public static Result genResultAndData(ResultEnum resultEnum, Object data) {
        return new Result(resultEnum,data);
    }
    public static Result genResult(ResultEnum resultEnum) {
        return new Result(resultEnum);
    }

    public static Result genExceptionResult(Exception e) {
        return new Result(e);
    }
    public static Result genFailResultAddressNull() {
        return new Result(ResultEnum.ADDRESSNULL);
    }
}

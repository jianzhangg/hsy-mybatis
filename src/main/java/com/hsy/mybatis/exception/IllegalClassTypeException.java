package com.hsy.mybatis.exception;

import org.springframework.dao.DataAccessException;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月3日 上午9:55:45
 */
public class IllegalClassTypeException extends DataAccessException {

    private static final long serialVersionUID = -6243321290931560158L;

    public static final String ERROR_ILLEGAL_CLASS_TYPE = "The illegal Class Type of the argument.";

    public IllegalClassTypeException() {
        super("The illegal Class Type of the argument.");
    }

    public IllegalClassTypeException(String message) {
        super(message);
    }

    public IllegalClassTypeException(Throwable cause) {
        super("The illegal Class Type of the argument.", cause);
    }

    public IllegalClassTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

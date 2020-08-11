package com.liuym.fund.exception;

import com.liuym.fund.util.StringFormatter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName ExceptionUtils
 * @Description ExceptionUtils
 * @Author LIUYQ
 * @Date 2019/10/28 18:13
 * @Version 1.0
 */
public class ExceptionUtils {

    public static void outline(String outline) throws PlatformException {
        throwError(outline, null, null);
    }

    public static void throwError(Throwable cause) throws PlatformException {
        throwError(null, null, cause);
    }

    public static void throwError(String errorObject) throws PlatformException {
        throwError(null, errorObject);
    }

    public static void throwError(String outline, Object errorObject) throws PlatformException {
        throwError(outline, errorObject, null);
    }

    /**
     * @param outline
     * @param errorObject
     *          如果为String,则与args配合使用，类似日志的`{}`占位符作用
     * @param args
     *          最后一位为Throwable
     * @throws PlatformException
     */
    public static PlatformException throwError(String outline, Object errorObject, Object...args) throws PlatformException {
        throw newError(outline, getErrorObject(outline, errorObject, args), getCauseFromArgs(args));
    }

    public static PlatformException newError(String outline, String errorObject, Object...args) {
        return newError(outline, getErrorObject(outline, errorObject, args), getCauseFromArgs(args));
    }

    public static PlatformException newError(String outline, String errorObject, Throwable cause) {
        return new PlatformException(outline, errorObject, cause);
    }

    public static PlatformException newError(int errCode, String outline, String errorObject, Throwable cause) {
        return new PlatformException(errCode, outline, errorObject, cause);
    }

    private static String getErrorObject(String outline, Object errorObject, Object...args) {
        if (errorObject == null) {
            return null;
        }
        return buildErrorObject(errorObject, args);
    }

    private static String buildErrorObject(Object errorObject, Object...args) {
        if (errorObject instanceof String) {
            String errorStr = (String) errorObject;
            if (StringUtils.isNotBlank(errorStr) && args != null && args.length > 0) {
                String res = StringFormatter.format(errorStr, args);
                return res;
            }
        }
        return errorObject.toString();
    }

    private static Throwable getCauseFromArgs(Object[] args) {
        Throwable cause = null;
        if (args != null && args.length > 0) {
            Object t = args[args.length - 1];
            if (t instanceof Throwable) {
                if (t instanceof InvocationTargetException) {
                    cause = ((InvocationTargetException) t).getTargetException();
                } else {
                    cause = (Throwable) t;
                }
            }
        }
        return cause;
    }
}

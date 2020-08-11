package com.liuym.fund.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName PlatformException
 * @Description 平台統一异常
 * @Author LIUYQ
 * @Date 2019/10/18 11:17
 * @Version 1.0
 */
public class PlatformException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public PlatformException(Throwable cause) {
        this(null, cause.getMessage(), cause);
    }

    public PlatformException(String errorObject) {
        this(errorObject, errorObject, null);
    }

    public PlatformException(String errorObject, Throwable cause) {
        this(errorObject, errorObject, cause);
    }

    public PlatformException(String outline, String errorObject) {
        this(outline, errorObject, null);
    }

    public PlatformException(String outline, Object errorObject, Throwable cause) {
        super(ServiceErrorStatus.INTERNAL_SERVER_ERROR.getErrCode(), StringUtils.defaultIfEmpty(outline, ServiceErrorStatus.INTERNAL_SERVER_ERROR.getOutline()), errorObject, cause);
    }

    public PlatformException(int errCode, String outline, Object errorObject, Throwable cause) {
        super(errCode, StringUtils.defaultIfEmpty(outline, ServiceErrorStatus.valueOf(errCode).getOutline()), errorObject, cause);
    }
}

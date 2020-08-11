package com.liuym.fund.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ServiceException
 * @Description ServiceException
 * @Author LIUYQ
 * @Date 2019/10/28 17:34
 * @Version 1.0
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 未定义的异常，通常是服务异常。
     */
    public static final int DEFAULT_ERROR_CODE = ServiceErrorStatus.INTERNAL_SERVER_ERROR.getErrCode();

    protected int errCode;
    /**
     * 方便排查错误详细异常说明，对排查异常有帮助，但是不能抛到客户端的异常说明信息。
     * 1、String 异常详细说明 (String)，
     * 2、DTO 对象参数 (toString)
     * 3、BO 对象参数 (toString)
     * 4、Array 数组对象参数 (toString)
     */
    protected Object errorObject;

    //概要信息
    protected String outline;

    public int getErrCode() {
        return errCode;
    }

    public Object getErrorObject() {
        return errorObject;
    }

    public String getOutline() {
        return outline;
    }

    public ServiceException(String outline, Throwable cause) {
        this(DEFAULT_ERROR_CODE, outline, null, cause);
    }

    public ServiceException(int errCode, String outline) {
        this(errCode, outline, null, null);
    }

    public ServiceException(String outline) {
        super(outline);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }


    /**
     * @param errCode
     *          错误代码，默认异常代码为{@link ServiceException#DEFAULT_ERROR_CODE}。
     * @param outline
     *          异常概要提示，展示到前端：系统异常、参数不合法、用户无权操作等说明，通常用于显示到客户端展示。
     * @param errorObject
     *          发生异常时的详细参数，也可以是详细描述，该字段值将会直接调用toString方法直接打印到日志中。
     *          在具体业务中时，不能传空，必须是异常详细说明及其异常参数信息。
     *          在其他系统框架层次，传null时，将会取异常的信息(cause.toString())
     * @param cause
     *          上层异常堆栈信息
     */
    public ServiceException(int errCode, String outline, Object errorObject, Throwable cause) {
        super(buildDetailMessage(errCode, outline, errorObject, cause), cause);
        this.outline = StringUtils.defaultIfEmpty(outline, ServiceErrorStatus.valueOf(errCode).getOutline());
        this.errCode = errCode;
        this.errorObject = errorObject;
    }

    /**
     * 构建详细的异常信息：[{errCode}], {outline}\t[cause.class]: {errorObject/cause}
     * @param errCode
     * @param outline
     * @param errorObject
     * @param cause
     * @return
     */
    private static String buildDetailMessage(int errCode, String outline, Object errorObject, Throwable cause) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(errCode);
        sb.append("], ");
        sb.append(outline);
        // 如果是字符串类型，则认为是详细说明，追加到异常信息中。
        // 否则，视为异常参数，交由日志打印。
        if (errorObject instanceof String
                && ((String) errorObject).length() > 0) {
            sb.append(" >>> ");
            if (cause != null) {
                sb.append(cause.getClass().getName());
                sb.append(": ");
            }
            sb.append((String) errorObject);
        }
        // 没有传errorObject，就把异常追加到详细信息里面
        else if (errorObject == null
                && cause != null) {
            sb.append(" >>> ");
            sb.append(cause.toString());
        }
        return sb.toString();
    }
}
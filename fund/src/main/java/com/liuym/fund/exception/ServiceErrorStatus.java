package com.liuym.fund.exception;

public enum ServiceErrorStatus {
    INTERNAL_SERVER_ERROR(500, "系统异常");

    /**
     * @param errCode
     * @return
     */
    public static ServiceErrorStatus valueOf(int errCode) {
        for (ServiceErrorStatus s : values()) {
            if (s.errCode == errCode) {
                return s;
            }
        }
        return null;
    }


    public int getErrCode() {
        return errCode;
    }

    public String getOutline() {
        return outline;
    }

    private int errCode;
    private String outline;

    ServiceErrorStatus(int errCode, String outline) {
        this.errCode = errCode;
        this.outline = outline;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }
}

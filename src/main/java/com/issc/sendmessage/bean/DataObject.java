package com.issc.sendmessage.bean;

import java.util.List;

/**
 * 实体类A
 */

public class DataObject {
    private int error_code;
    private String reason;
    private List<Results> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public void setResult(List<Results> result) {
        this.result = result;
    }


    public List<Results> getResult() {

        return result;
    }

    @Override
    public String toString() {
        return "DataObject [error_code=" + error_code + ", reason=" + reason + ", result=" + result.toString() + "]";
    }
}



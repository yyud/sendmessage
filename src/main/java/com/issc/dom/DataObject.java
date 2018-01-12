package com.issc.dom;

import java.util.List;

public class DataObject {
    private int error_code;
    private String reason;
    private List<Result> result;

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


    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {

        return result;
    }

    @Override
    public String toString() {
        return "DataObject [error_code=" + error_code + ", reason=" + reason + ", result=" + result.toString() + "]";
    }


}

class Result {

    private String content;
    private String hashId;
    private int unixtime;
    private String updatetime;
    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }



    @Override
    public String toString() {
        return "DetailsList [content=" + content + ", hashId=" + hashId + ", unixtime=" + unixtime + ", updatetime=" + updatetime + "]";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

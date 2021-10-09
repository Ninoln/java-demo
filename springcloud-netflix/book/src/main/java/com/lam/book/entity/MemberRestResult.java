package com.lam.book.entity;

public class MemberRestResult {

    private String code;
    private String message;
    private MemberDTO data;

    @Override
    public String toString() {
        return "RestResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MemberDTO getData() {
        return data;
    }

    public void setData(MemberDTO data) {
        this.data = data;
    }
}

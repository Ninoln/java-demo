package com.lam.book.entity;

import java.util.Date;

public class MemberDTO {

    private Long mid;
    private String name;
    private String idno;
    private String mobile;
    private Date regdate;
    private Date expdate;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", idno='" + idno + '\'' +
                ", mobile='" + mobile + '\'' +
                ", regdate=" + regdate +
                ", expdate=" + expdate +
                '}';
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getExpdate() {
        return expdate;
    }

    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }
}

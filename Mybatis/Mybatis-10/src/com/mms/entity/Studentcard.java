package com.mms.entity;

public class Studentcard {
    private Integer cardno;

    private String cardinfo;

    public Integer getCardno() {
        return cardno;
    }

    public void setCardno(Integer cardno) {
        this.cardno = cardno;
    }

    public String getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo == null ? null : cardinfo.trim();
    }
}
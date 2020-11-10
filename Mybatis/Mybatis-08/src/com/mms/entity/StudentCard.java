package com.mms.entity;

//学生证表
public class StudentCard {
    private int cardNo;
    private String cardInfo;

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "cardNo=" + cardNo +
                ", cardInfo='" + cardInfo + '\'' +
                '}';
    }
}

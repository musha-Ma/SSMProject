package com.mms.entity;

//学生证
public class StudentCard {
    private int cardID;
    private String cardInfo;

    public StudentCard() {
    }

    public StudentCard(int cardID, String cardInfo) {
        this.cardID = cardID;
        this.cardInfo = cardInfo;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getStuInfo() {
        return cardInfo;
    }

    public void setStuInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "cardID=" + cardID +
                ", cardInfo='" + cardInfo + '\'' +
                '}';
    }
}

package com.mms.entity;

//业务扩展类，扩展了学生类和学生证类（业务扩展类一般选择继承属性多的类）
public class StudentAndCard extends Student {
    /*
        由于StudentAndCard继承自Student类，所以已经具备了Student类的属性，但是还不具备StudentCard
        类的属性，所以要扩展StudentCard类的属性
     */
    private int cardID;
    private String cardInfo;

    public StudentAndCard() {
    }

    public StudentAndCard(int cardID, String cardInfo) {
        this.cardID = cardID;
        this.cardInfo = cardInfo;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    public String toString() {
        return super.toString()+"---"+"StudentAndCard{" +
                "cardID=" + cardID +
                ", cardInfo='" + cardInfo + '\'' +
                '}';
    }
}

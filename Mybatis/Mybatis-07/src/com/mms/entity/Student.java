package com.mms.entity;

/**
 * 实现一对一方式二：使用resultMap
 *  1、我们之前的业务扩展类的基本思想是创建了一个子类分别继承Student类的属性和StuentCard类的属性，
 *     在逻辑上StudentAndCard类分别对应一对一查到的10个属性，因此使用resultMap大致也是这种思想
 *  2、我们只需要将属性少的类作为属性多的类的一个属性（成员变量）即可
 */
public class Student {
    //学生类属性
    private int stuNo;
    private String stuName;
    private int stuAge;
    private String graName;
    private int CID;
    private int classNo;
    //学生证属性
    private StudentCard studentCard;

    public Student() {
    }

    public Student(int stuNo, String stuName, int stuAge, String graName, int CID, int classNo) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.graName = graName;
        this.CID = CID;
        this.classNo = classNo;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getGraName() {
        return graName;
    }

    public void setGraName(String graName) {
        this.graName = graName;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    //一对一toString()
    /*@Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", graName='" + graName + '\'' +
                ", CID=" + CID +
                ", classNo=" + classNo +
                '}'+"--------"+this.studentCard.toString();
    }*/

    //一对多toString()

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", graName='" + graName + '\'' +
                ", CID=" + CID +
                ", classNo=" + classNo +
                '}';
    }
}




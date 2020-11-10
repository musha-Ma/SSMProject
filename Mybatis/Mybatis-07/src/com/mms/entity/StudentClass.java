package com.mms.entity;

        import java.util.List;

//班级类
public class StudentClass {
    //班级属性
    private int classId;
    private String classInfo;

    /**
     * 一对多与一对一不同的是，一对一将属性少的类作为属性多的类的一个属性（成员变量）即可，而一对多
     * 则恰好相反，因为一个班级有好多学生
     */
    private List<Student> studentList;//通过该成员属性让班级和学生建立起联系
    public StudentClass() {
    }

    public StudentClass(int classId, String classInfo) {
        this.classId = classId;
        this.classInfo = classInfo;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classId=" + classId +
                ", classInfo='" + classInfo + '\'' +
                '}'+"--------"+this.studentList.toString();
    }
}

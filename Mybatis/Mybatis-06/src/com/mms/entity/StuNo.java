package com.mms.entity;

import java.util.List;

//包含学生学号的类
public class StuNo {
    //包含学生学号的属性
    private List<Integer> stuNos;

    public StuNo() {
    }

    public StuNo(List<Integer> stuNos) {
        this.stuNos = stuNos;
    }

    public List<Integer> getStuNo() {
        return stuNos;
    }

    public void setStuNo(List<Integer> stuNos) {
        this.stuNos = stuNos;
    }
}

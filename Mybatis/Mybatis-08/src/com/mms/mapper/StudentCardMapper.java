package com.mms.mapper;

import com.mms.entity.StudentCard;

public interface StudentCardMapper {
    /**
     * 查询学生证信息
     */
    StudentCard queryByCardID(int cardID);
}

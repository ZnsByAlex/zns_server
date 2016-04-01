package com.zns.app.dao;

import com.zns.app.bean.Line;

public interface ILineDao {
    int deleteByPrimaryKey(Integer lindId);

    int insert(Line record);

    int insertSelective(Line record);

    Line selectByPrimaryKey(Integer lindId);

    int updateByPrimaryKeySelective(Line record);

    int updateByPrimaryKey(Line record);
    
    Line selectByExamId(Integer examId);
}
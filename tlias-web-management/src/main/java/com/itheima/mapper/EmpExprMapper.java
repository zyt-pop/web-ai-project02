package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 批量插入员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    void insertBatch(List<EmpExpr> exprList);
}

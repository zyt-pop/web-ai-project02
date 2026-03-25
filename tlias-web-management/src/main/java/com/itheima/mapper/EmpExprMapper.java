package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id批量删除员工工作信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}

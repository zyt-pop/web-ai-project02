package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    //---------------------------------原始分页查询实现方式---------------------------

//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id=d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //---------------------------------PageHelper分页查询实现方式---------------------------
    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    public List<Emp> list();
}

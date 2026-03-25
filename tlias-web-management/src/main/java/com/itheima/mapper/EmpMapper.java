package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
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
    //@Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);

   // @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Emp emp);

    /**
     * 根据ID批量删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工
     * @param empId
     * @return
     */
    Emp getById(Integer empId);

    void updateById(Emp emp);
}

package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 查询所有员工
     */
    List<Emp> list();

    /**
     * 新增员工
     */
    void save(Emp emp) throws Exception;

    /**
     * 删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查找员工信息----为了在修改员工信息前实现数据回显
     * @param empId
     * @return
     */
    Emp getById(Integer empId);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);



    //判断部门下是否有员工
    List<Emp> isHasEmp(Integer deptId);
}

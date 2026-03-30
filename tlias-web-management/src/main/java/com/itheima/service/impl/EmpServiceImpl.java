package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
    /**
     * PageHelper分页查询
     */
//        @Override
//        public PageResult<Emp>page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end){
//            //1.设置分页参数
//            PageHelper.startPage(page,pageSize);//拦截器拦截，将分页参数设置到sql中，并执行查询
//            //2.执行查询
//            List<Emp> empList = empMapper.list(name,gender,begin,end);
//            //3.解析查询结果，并封装
//            Page<Emp> p = (Page<Emp>)empList;//empList 能直接强转为 Page<Emp>是因为PageHelper拦截了Mapper查询方法，返回的不是普通List，而是Page对象。
//                                            //Page extends ArrayList implements List,这实际是向下转型
//            return new PageResult<Emp>(p.getTotal(),p.getResult());
//        }


    /**
     * 条件查询
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
            //1.设置分页参数
            PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());//拦截器拦截，将分页参数设置到sql中，并执行查询
            //2.执行查询
            List<Emp> empList = empMapper.list(empQueryParam);
            //3.解析查询结果，并封装
            Page<Emp> p = (Page<Emp>)empList;//empList 能直接强转为 Page<Emp>是因为PageHelper拦截了Mapper查询方法，返回的不是普通List，而是Page对象。
                                            //Page extends ArrayList implements List,这实际是向下转型
            return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    /**
     * 查询所有员工信息
     */
    @Override
    public List<Emp> list() {
        List<Emp> empList = empMapper.list(new EmpQueryParam());
        return empList;
    }

    @Transactional(rollbackFor = Exception.class)       //事务管理-默认出现运行时异常RuntimeException才会回滚
    @Override
    public void save(Emp emp) throws Exception {
        try {
            //保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //int i=1/0;

            //保存员工工作信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工信息："+emp);
            empLogService.insertLog(empLog);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteByIds(ids);
        //删除员工工作信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 根据id查员工
     */
    @Override
    public Emp getById(Integer empId) {
        Emp emp = empMapper.getById(empId);
        return emp;
    }
    /**
     * 修改员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.1 先根据ID删除员工的工作经历信息(数据库里的)
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        //2.2 再添加员工新的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr->{
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> isHasEmp(Integer deptId) {
        List<Emp> empList = empMapper.isHasEmp(deptId);
        return empList;
    }
}

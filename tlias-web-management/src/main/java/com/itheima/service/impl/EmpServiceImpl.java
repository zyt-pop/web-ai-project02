package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageResult<Emp >page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//        Integer start = (page-1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        return new PageResult<Emp>(total,rows);
//    }

    /**
     * PageHelper分页查询
     * @param page
     * @param pageSize
     * @return
     */
        @Override
        public PageResult<Emp>page(Integer page, Integer pageSize){
            //1.设置分页参数
            PageHelper.startPage(page,pageSize);//拦截器拦截，将分页参数设置到sql中，并执行查询
            //2.执行查询
            List<Emp> empList = empMapper.list();
            //3.解析查询结果，并封装
            Page<Emp> p = (Page<Emp>)empList;//empList 能直接强转为 Page<Emp>是因为PageHelper拦截了Mapper查询方法，返回的不是普通List，而是Page对象。
                                            //Page extends ArrayList implements List,这实际是向下转型
            return new PageResult<Emp>(p.getTotal(),p.getResult());
        }


}

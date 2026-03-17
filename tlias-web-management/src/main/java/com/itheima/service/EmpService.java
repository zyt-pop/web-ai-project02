package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}

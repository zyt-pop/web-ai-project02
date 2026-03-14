package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
     List<Dept> findAll();
}

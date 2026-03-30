package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    //分页条件查询学员信息
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    //新增学员信息
    void save(Student student);

    //根据id查询学员信息
    Student getById(Integer id);

    //更新学员信息
    void update(Student student);

    //批量删除学员信息
    void deleteByIds(List<Integer> ids);

    //违纪处理
    void violation(Integer id, Integer score);
}

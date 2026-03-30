package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());//拦截器拦截，将分页参数设置到sql中，并执行查询
        //2.执行查询
        List<Student> studentList = studentMapper.page(studentQueryParam);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Integer id) {
        Student student = studentMapper.getById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id,score);
    }
}

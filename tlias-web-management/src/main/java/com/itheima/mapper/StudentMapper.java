package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    //分页查询学员列表
    List<Student> page(StudentQueryParam studentQueryParam);
    //新增学员信息
    void insert(Student student);
    //根据id查询学员信息
    Student getById(Integer id);
    //修该学员信息
    void update(Student student);
    //批量删除学员信息
    void deleteByIds(List<Integer> ids);
    //学员违纪处理
    void violation(Integer id, Integer score);


    //统计各个班级的学员人数
    @MapKey("clazz")
    List<Map<String,Object>> countStudentClazzData();

    //统计学员的学历信息
    @MapKey("degree")
    List<Map<String,Object>> countStudentDegreeData();
}

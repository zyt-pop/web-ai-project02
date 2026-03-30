package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    //分页查询班级列表
    List<Clazz> page(ClazzQueryParam clazzQueryParam);
    //新增班级
    void insert(Clazz clazz);
    //根据id删除班级
    void deleteById(Integer id);
    //根据id查询班级
    Clazz getById(Integer id);

    void update(Clazz clazz);

//    List<Clazz> list();
}

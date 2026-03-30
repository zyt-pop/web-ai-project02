package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.GenderOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 统计各个职位员工人数
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2.组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    /**
     * 统计不同性别的员工数量
     * @return
     */
    @Override
    public List<Map<String,Object>>  getEmpGenderData() {
        List<Map<String, Object>> list = empMapper.countEmpGenderData();
        return list;
    }

    /**
     * 统计各个班级的人数
     * @return
     */
    @Override
    public ClazzOption getStudentClazzData() {
        List<Map<String, Object>> list = studentMapper.countStudentClazzData();
        List<Object> clazzList = list.stream().map(data -> (data.get("clazz"))).toList();
        List<Object> numsList = list.stream().map(data -> (data.get("num"))).toList();
        return new ClazzOption(clazzList,numsList);
    }

    /**
     * 统计学员的学历信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.countStudentDegreeData();
        return list;
    }
}

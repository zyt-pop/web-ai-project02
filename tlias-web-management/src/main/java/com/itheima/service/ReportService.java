package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.GenderOption;
import com.itheima.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ReportService {
    /**
     * 统计各个职位员工人数
     */
    JobOption getEmpJobData();

    List<Map<String,Object>> getEmpGenderData();

    ClazzOption getStudentClazzData();

    List<Map<String,Object>> getStudentDegreeData();
}

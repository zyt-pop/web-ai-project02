package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){

        log.info("🍎🍊🍉---请求分页参数{}",empQueryParam);
        PageResult<Emp> result = empService.page(empQueryParam);
        return Result.success(result);
    }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("🍎🍊🍉---保存员工信息{}",emp);
        empService.save(emp);
        return Result.success();
    }

}

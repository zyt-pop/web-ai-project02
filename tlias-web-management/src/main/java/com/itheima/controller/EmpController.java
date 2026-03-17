package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("emps")
@RestController
public class EmpController {
    /**
     * 分页查询
     */
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(@RequestParam(defaultValue ="1") Integer page,
                       @RequestParam(defaultValue ="10")Integer pageSize){

        log.info("❤❤❤---请求分页参数{},{}",page,pageSize);
        PageResult<Emp> result = empService.page(page,pageSize);
        return Result.success(result);
    }

}

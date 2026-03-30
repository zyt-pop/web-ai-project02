package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
     * 查询所有员工
     */
    @GetMapping("/list")
    public Result list(){
        List<Emp> empList = empService.list();
        return  Result.success(empList);
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

    /**
     * 删除员工信息
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("🍎🍊🍉---删除员工:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查员工信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer empId){
        log.info("🍎🍊🍉---根据ID查询员工:"+empId);
        Emp emp = empService.getById(empId);
        return Result.success(emp);
    }
    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("🍎🍊🍉---修改员工信息{}",emp);
        empService.update(emp);
        return Result.success();
    }



}

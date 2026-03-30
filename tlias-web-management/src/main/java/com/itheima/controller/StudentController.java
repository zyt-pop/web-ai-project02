package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(("/students"))
public class StudentController {
    @Autowired
    private StudentService studentService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("🍎🍊🍉---学员分页查询参数{}",studentQueryParam);
        PageResult<Student> result = studentService.page(studentQueryParam);
        return Result.success(result);
    }
    /**
     * 添加学员
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("🍎🍊🍉---要添加的学员信息:{}",student);
        studentService.save(student);
        return Result.success();
    }
    /**
     * 根据id查询学员信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("🍎🍊🍉---根据ID查询学员信息{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修该学员信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }
    /**
     * 删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("🍎🍊🍉---删除学员id:{}",ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("🍎🍊🍉---违纪处理,id:{},score:{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}

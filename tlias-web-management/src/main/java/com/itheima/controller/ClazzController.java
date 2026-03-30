package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /**
     * 分页查询
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("🍎🍊🍉---请求分页参数{}",clazzQueryParam);
        PageResult<Clazz> result = clazzService.page(clazzQueryParam);
        return Result.success(result);
    }
    /**
     * 新增班级
     */
    @PostMapping
    public  Result save(@RequestBody Clazz clazz) {
        log.info("🍎🍊🍉---保存班级信息{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }
    /**
     * 根据ID查询班级信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        log.info("🍎🍊🍉---根据ID查询班级信息{}",id);
        if (id == null) {
            return Result.error("班级 ID 不能为空");
        }
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("🍎🍊🍉---删除班级id:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查询所有班级,   给新增学员界面使用
     */
    @GetMapping("/list")
    public Result list(){
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}

package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     */
    @GetMapping
    public Result list(){
        List<Dept> lists = deptService.findAll();
        return Result.success(lists);
    }
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer id){//@RequestParam注解必须传递参数（required为true），否则报错
//        System.out.println("根据id删除数据："+id);
//        return Result.success();
//    }

    /**
     * 根据ID删除部门
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id){//方法形参和url传递的参数名一样的时候，可以省略注解
        //System.out.println("根据id删除数据："+id);
        log.info("根据id删除数据："+id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门"+dept);
        log.info("新增部门"+dept);
        deptService.add(dept);
        return Result.success();
    }


    /**
     * 根据ID查询部门
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        //System.out.println("根据ID查询部门:"+deptId);
        log.info("根据ID查询部门:"+deptId);
        Dept dept = deptService.getById(deptId);
        return Result.success(dept);
    }
    /**
     * 根据ID修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("根据ID修改部门:"+dept);
        log.info("根据ID修改部门:"+dept);
        deptService.update(dept);
        return Result.success();
    }

}

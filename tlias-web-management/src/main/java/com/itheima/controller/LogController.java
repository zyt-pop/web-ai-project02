package com.itheima.controller;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping("/page")
    public Result page(@RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize")Integer pageSize){
        PageResult<OperateLog> logList = logService.page(page, pageSize);
        return Result.success(logList);
    }
}

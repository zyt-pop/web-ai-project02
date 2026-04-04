package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface LogService {
    public PageResult<OperateLog> page(Integer page, Integer pageSize);
}

package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 条件分页查询班级信息
     * @param clazzQueryParam
     * @return
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());//拦截器拦截，将分页参数设置到sql中，并执行查询
        //2.执行查询
        List<Clazz> clazzList = clazzMapper.page(clazzQueryParam);
        //3.解析查询结果，并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        //clazzList 能直接强转为 Page<Clazz>是因为PageHelper拦截了Mapper查询方法，返回的不是普通List，而是Page对象。
        //Page extends ArrayList implements List,这实际是向下转型

        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    /**
     * 新增班级
     * @param clazz
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据id查询班级
     * @param id
     * @return
     */
    @Override
    public Clazz getById(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        return clazz;
    }

    /**
     * 根据id删除班级信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * 修改班级信息
     * @param clazz
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> list() {
        return clazzMapper.page(new ClazzQueryParam());
    }
}

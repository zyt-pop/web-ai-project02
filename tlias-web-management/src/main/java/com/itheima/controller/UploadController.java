package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     *本地磁盘存储方案
     */
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接受参数：{},{},{}",name,age,file);
//        //获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//        //新文件名
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString()+extension;
//        //保存文件
//        file.transferTo(new File("E:/IdeaProjects/images/" + newFileName));
//        return Result.success();
//    }
    /**
     * 阿里云OSS存储方案
     */

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接受参数：{}",file.getOriginalFilename());
        //将文件交给阿里云OSS存储
        String url = aliyunOSSOperator.upload(file.getBytes()/**获得字节数组**/, file.getOriginalFilename());
        log.info("文件上传url：{}",url);

        return Result.success(url);
    }
}

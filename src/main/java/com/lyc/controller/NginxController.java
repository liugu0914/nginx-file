package com.lyc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyc.service.NginxService;
import com.lyc.sys.FileResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.lyc.controller
 * @Author: lyc
 * @Date: 2019/10/4 20:17
 */
@RestController
@Slf4j
public class NginxController {

    @Autowired
    private NginxService nginxService;

    /**
     * 可上传图片、视频，只需在nginx配置中配置可识别的尾缀
     */
    @PostMapping("/upload")
    public String pictureUpload(@RequestParam(value = "file") MultipartFile uploadFile) throws  Exception {
        long begin = System.currentTimeMillis();
        List<FileResult> list= nginxService.uploads(new MultipartFile[]{uploadFile});
        // 浏览器擅长处理json格式的字符串，为了减少因为浏览器内核不同导致的bug，建议用json
        FileResult result=null;
        if (list!=null && !list.isEmpty())
            result=list.get(0);
        String json = new ObjectMapper().writeValueAsString(result);
        long end = System.currentTimeMillis();
        log.info("共耗时：[" + (end-begin) + "]毫秒");
        return json;
    }

    @PostMapping("/uploads")
    public Object picturesUpload(@RequestParam(value = "file") MultipartFile[] uploadFile) throws  Exception{
        long begin = System.currentTimeMillis();
        List<FileResult> list=nginxService.uploads(uploadFile);
        long end = System.currentTimeMillis();
        log.info("共耗时：[" + (end-begin) + "]毫秒");
        return list;
    }

}

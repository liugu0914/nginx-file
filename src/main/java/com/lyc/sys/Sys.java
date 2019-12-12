package com.lyc.sys;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Sys {

    /**
     * 存放图片的根目录
     */
    @Value("${nginx.rootPath}")
    private  String rootPath;

    /**
     * 存放图片的路径
     */
    @Value("${nginx.imgUrl}")
    private  String imgUrl;

}

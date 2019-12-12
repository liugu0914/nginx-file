package com.lyc.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Package: com.lyc.util
 * @Author: lyc
 * @Date: 2019/10/4 20:17
 */
public class IDUtils {

    /**
     * 生成随机图片名
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        
        return str;
    }

    /**
     * 获取uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
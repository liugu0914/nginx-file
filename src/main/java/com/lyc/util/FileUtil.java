package com.lyc.util;

import com.lyc.sys.Sys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @Package: com.lyc.util
 * @Author: lyc
 * @Date: 2019/10/4 20:17
 */
@Component
@Slf4j
public class FileUtil {

    @Autowired
    private  Sys sys;

    /**
     * ftp上传图片
     * @param inputStream 图片io流
     * @param imagePath 路径，不存在就创建目录
     * @param imagesName 图片名称
     * @return urlStr 图片的存放路径
     */
    public  String putImages(InputStream inputStream, String imagePath, String imagesName){
        //处理返回的路径
        String resultFile="";
        FileOutputStream out=null;
        try {
            String path = sys.getRootPath() + imagePath + "/";
            createDir(path);
            out = new FileOutputStream(path+imagesName);
            //创建一个缓冲区
            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完的标识
            int len = 0;
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while((len=inputStream.read(buffer))>0){
                //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                out.write(buffer, 0, len);
            }

            resultFile = sys.getImgUrl() + imagePath + imagesName;
        } catch (Exception e) {
            log.error("上传失败：" + e.getMessage());
        }finally {
            try {
                out.flush();
                out.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultFile;
    }

    /**
     * 创建目录
     */
    private static void createDir(String path)  {
        File file=new File(path);
        if (!file.exists())
            file.mkdirs();
    }
//
//    /**
//     * 删除图片
//     */
//    public static void delImages(String imagesName){
//        try {
//            ChannelSftp sftp = getChannel();
//            String path = rootPath + imagesName;
//            sftp.rm(path);
//            sftp.quit();
//            sftp.exit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
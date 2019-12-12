package com.lyc.service;

import com.lyc.dao.FileResultDao;
import com.lyc.sys.Constant;
import com.lyc.sys.FileResult;
import com.lyc.util.FileUtil;
import com.lyc.util.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.lyc.service
 * @Author: lyc
 * @Date: 2019/10/4 21:34
 */
@Service
@Slf4j
public class NginxService {

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private FileResultDao dao;

    public synchronized FileResult uploadPicture(MultipartFile uploadFile) throws Exception {
        log.info("上传文件开始");
        FileResult fileResult = new FileResult(uploadFile);
        if (fileResult.getSize() > Constant.FILE_MAX_SIZE)
            throw new Exception("文件最大不能超过" + Constant.FILE_MAX_SIZE_STR);
        FileResult file = dao.queryOne("fileResult.getFilebyMD5", fileResult.getMd5());
        if (file != null)
            return file;
        //1.1获取原始文件名
        String oldName = fileResult.getFilename();
        //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
        String uuid = IDUtils.getUUID();
        assert oldName != null;
        String newName = uuid + "." + fileResult.getType();
        //1.3生成文件在服务器端存储的子目录
        String filePath = new DateTime().toString("/yyyyMMdd/");

        //2、把图片上传到图片服务器
        //2.1获取上传的io流
        InputStream input = null;
        try {
            input = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2调用fileUtil工具类进行上传
        String url = fileUtil.putImages(input, filePath, newName);
        fileResult.setUrl(url);
        if (fileResult != null) {
            fileResult.setId(uuid);
            log.info(fileResult.toString());
            boolean insert = dao.add("fileResult.save", fileResult);
            if (insert)
                log.info("文件插入成功");
        }
        return fileResult;
    }

    @Transactional
    public List<FileResult> uploads(MultipartFile[] files) throws Exception {
        if (files==null || files.length==0)
            throw  new Exception("文件为空");
        List<FileResult> list=new ArrayList<FileResult>(files.length);
        for (MultipartFile file : files) {
            list.add(uploadPicture(file));
        }
        return  list;
    }

}

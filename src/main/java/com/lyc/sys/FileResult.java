package com.lyc.sys;

import lombok.Data;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileResult extends Base {

    private static final long serialVersionUID = 6126779975341599704L;
    /**
     * id
     */
    private String id;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件MD5
     */
    private String md5;

    /**
     * 文件类型
     */
    private String type;

    public FileResult(MultipartFile uploadFile) {
        if (uploadFile == null)
            return;
        String filename = uploadFile.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf(".") + 1);
        long size = uploadFile.getSize();
        //md5生成
        String md5str = filename + "#" + size;
        this.filename = filename;
        this.size = size;
        this.type = type;
        this.md5 = DigestUtils.md5DigestAsHex(md5str.getBytes());
    }
}

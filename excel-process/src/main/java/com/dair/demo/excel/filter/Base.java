package com.dair.demo.excel.filter;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @description: dto基础类
 * @author:
 * @date : 2020/10/26 11:46 上午
 **/
@Data
public abstract class Base {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String updater;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 上传的文件
     */
    private MultipartFile file;

}



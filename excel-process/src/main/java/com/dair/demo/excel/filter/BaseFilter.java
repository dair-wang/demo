package com.dair.demo.excel.filter;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public abstract class BaseFilter {

    /**
     * 当前显示的是第几页，从第1页开始
     */
    private int pageNum = 1;

    /**
     * 每页显示的记录数
     */
    private int pageSize = 20;

    /**
     * 主键
     */
    private List<Long> ids;

    /**
     * 项目创建人
     */
    private String creator;

}

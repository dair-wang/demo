package com.dair.demo.excel.annotation;

import java.lang.annotation.*;

/**
 * @description: excel 解析策略
 * @author: wanghaili
 * @date : 2020/10/26 11:09 上午
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ParsePolicy {

    /**
     * 解析支持最大行数
     */
    long maxRows() default 100000;

    /**
     * 出现错误是否继续解析
     *
     * @return true:出错停止解析； false：出错继续解析
     */
    boolean failFast() default false;

    /**
     * 上传数据存在是否覆盖
     *
     * @return true:存在则覆盖； false：存在不覆盖，进行提示
     */
    boolean existReplace() default false;
}

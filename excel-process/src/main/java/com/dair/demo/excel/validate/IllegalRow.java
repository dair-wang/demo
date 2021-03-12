package com.dair.demo.excel.validate;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 错误行
 * @author: wanghaili
 * @date : 2020/11/15 10:39 上午
 **/
@Data
@AllArgsConstructor
public class IllegalRow<T> {

    /**
     * 校验结果
     */
    private ValidateResult validateResult;
    /**
     * 校验的实体
     */
    private T row;

}

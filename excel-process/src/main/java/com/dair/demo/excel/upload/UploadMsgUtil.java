package com.dair.demo.excel.upload;

import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.validate.IllegalRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @description: 构建导出list工具类
 * @author wanghaili18
 * @date: 20-12-18 下午7:48
 */
public class UploadMsgUtil {

    /**
     * 错误行数
     */
    private static final String ERROR_ROW_NUMBER = "错误行数";

    /**
     * 错误提示列
     */
    private static final String ERROR_MSG_TITLE = "错误内容";

    /**
     * 获取导出的数据
     *
     * @param illegalData 校验失败的数据
     * @param header      表头list
     * @return
     */
    public static List<List> getExportList(Map<Integer, ? extends IllegalRow<? extends Base>> illegalData, List<List<String>> header) {

        //构建表头
        header.add(Arrays.asList(ERROR_ROW_NUMBER));
        header.add(Arrays.asList(ERROR_MSG_TITLE));

        //构建表内容
        return illegalData.entrySet().stream().map(entry -> {
            List<String> list = new ArrayList<>();
            list.add(entry.getKey().toString());
            list.add(entry.getValue().getValidateResult().getMessage());
            return list;
        }).collect(Collectors.toList());
    }
}

package com.dair.demo.excel.upload;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/*
 * @description: 设置单元格格式
 * @author wanghaili18
 * @date: 20-12-18 下午6:47
 */
public class WriteHandler {

    /**
     * 设置excel导出样式
     *
     * @return HorizontalCellStyleStrategy
     */
    public static HorizontalCellStyleStrategy headStyle() {
        // 头部样式策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE1.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 11);
        headWriteFont.setBold(false);
        headWriteCellStyle.setWrapped(false);
        headWriteCellStyle.setWriteFont(headWriteFont);
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
        headWriteCellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headWriteCellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headWriteCellStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());

        // 内容样式策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        contentWriteCellStyle.setWrapped(false);

        return new HorizontalCellStyleStrategy(headWriteCellStyle,
                contentWriteCellStyle);
    }
}

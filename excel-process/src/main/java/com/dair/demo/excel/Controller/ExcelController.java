package com.dair.demo.excel.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.dair.demo.excel.SimpleRowData;
import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.filter.BaseFilter;
import com.dair.demo.excel.upload.UploadListener;
import com.dair.demo.excel.upload.UploadMsgUtil;
import com.dair.demo.excel.upload.WriteHandler;
import com.dair.demo.excel.utils.ResponseUtil;
import com.dair.demo.excel.validate.IDataValidate;
import com.dair.demo.excel.validate.IllegalRow;
import com.dair.demo.excel.validate.ValidateExample;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    /**
     * 上传
     *
     * @param file
     * @param req
     * @param res
     */
    @SneakyThrows
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "filters", required = false) String filters,
                       HttpServletRequest req,
                       HttpServletResponse res) {

        //创建临时文件
        File demandFile = File.createTempFile(file.getName(), ExcelTypeEnum.XLSX.getValue());
        file.transferTo(demandFile);
        IDataValidate<Base, Base, BaseFilter> validator = new ValidateExample();

        BaseFilter filter = new BaseFilter() {
            @Override
            public int getPageNum() {
                return super.getPageNum();
            }
        };
        Class<? extends Base> clazz = SimpleRowData.class;
        UploadListener<Base> listener = new UploadListener<>(validator, filter, clazz);
        EasyExcelFactory.read(demandFile, clazz, listener).sheet().autoTrim(true).doRead();

        if (listener.hasError()) {
            File targetFile = File.createTempFile(file.getName() + "错误信息提示",
                    ExcelTypeEnum.XLSX.getValue());
            ResponseUtil.renderResponseHeader(res, req, targetFile.getName());
            Map<Integer, ? extends IllegalRow<? extends Base>> illegalData = listener.getIllegalData();
            List<List<String>> header = new ArrayList<>();
            List<List> dataList = UploadMsgUtil.getExportList(illegalData, header);
            EasyExcel.write(res.getOutputStream()).registerWriteHandler(WriteHandler.headStyle()).head(header).sheet()
                    .doWrite(dataList);
        } else {
            System.out.println("success");
        }

    }

    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }

}
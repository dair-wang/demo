package com.dair.demo.excel.upload;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.dair.demo.excel.annotation.ParsePolicy;
import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.filter.BaseFilter;
import com.dair.demo.excel.validate.IDataValidate;
import com.dair.demo.excel.validate.IllegalRow;
import com.dair.demo.excel.validate.ValidateResult;
import com.dair.demo.excel.validate.WeightContext;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static com.dair.demo.excel.validate.ValidateResult.ofFailed;

@Slf4j
public class UploadListener<T extends Base> extends AnalysisEventListener<T> {

    /**
     * parse success data :Map<line, data>
     */
    private final Map<Integer, T> successRows = new LinkedHashMap<>();
    /**
     * parse error data : Map<line, data>
     */
    private final Map<Integer, IllegalRow<T>> illegalRows = new LinkedHashMap<>();
    /**
     * 解析是否有错误
     */
    private boolean hasError;
    /**
     * 表头信息
     */
    private Map<Integer, String> headMap;

    /**
     * row data validator
     */
    private final IDataValidate<T, T, BaseFilter> validator;

    /**
     * upload context
     */
    private final WeightContext weightContext = new WeightContext();

    /**
     * excel解析辅助条件
     */
    private final BaseFilter filter;

    /**
     * excel parse conf
     */
    private final ParsePolicy parsePolicy;




    public UploadListener(IDataValidate<T, T, BaseFilter> validator, BaseFilter filter,Class<?> clazz) {
        this.validator = validator;
        this.filter = filter;
        parsePolicy = clazz.getAnnotation(ParsePolicy.class);
        Objects.requireNonNull(parsePolicy);
        weightContext.putContainerByKey(BaseFilter.class.getName(), filter);
    }

    @Override
    public void invoke(T row, AnalysisContext context) {

        Preconditions.checkState(!(hasError), "解析上传文件发送错误,请检查");
        int lineNumber = context.readRowHolder().getRowIndex();
        //校验最大行数
        if (lineNumber > 100000) {
            ValidateResult exceeded = ofFailed("超出最大行数，忽略不做处理");
            illegalRows.put(lineNumber, new IllegalRow<>(exceeded, row));
            return;
        }
        System.out.println(row);
        //normal validate
//        ValidateResult validate = DataValidateHelper.validateEntity(row);
//        if (validate.isSuccess()) {
//            successRows.put(lineNumber, row);
//        } else {
//            hasError = true;
//            illegalRows.put(lineNumber, new IllegalRow<>(validate, row));
//        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (Objects.nonNull(validator) && successRows.size() > 0) {
            //
            Map<Integer, IllegalRow<T>> validate = validator.insertValidate(successRows, parsePolicy, weightContext);
            validate.forEach((k, v) -> successRows.remove(k));
            checkSame();
            illegalRows.putAll(validate);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap = headMap;
    }

    /**
     * 校验上传数据中的重复项
     */
    private void checkSame() {
        List<T> list = new ArrayList();
        for (Map.Entry<Integer, T> entry : successRows.entrySet()) {
            if (list.contains(entry.getValue())) {
                ValidateResult validateResult = new ValidateResult(false, "上传的数据重复,请删除后重新上传");
                illegalRows.put(entry.getKey(), new IllegalRow<>(validateResult, entry.getValue()));
            } else {
                list.add(entry.getValue());
            }
        }
    }

    /**
     * 存在线程安全问题，单线程串行执行后通过此api获取解析的数据
     *
     * @return excel解析后的数据
     */
    public List<Object> getSuccessData() {

//        if (Objects.nonNull(validator)) {
//            return validator.insertTransform(successRows.values(), filter, weightContext);
//        }
        return new ArrayList<>(successRows.values());
    }

    /**
     * 存在线程安全问题，单线程串行执行后通过此api获取解析的数据
     *
     * @return excel解析后的数据
     */
    public Map<Integer, IllegalRow<T>> getIllegalData() {

        return illegalRows;
    }

    /**
     * 获取表头
     *
     * @return
     */
    public List<List<String>> getHeader() {
        return this.headMap.values().stream().map(Lists::newArrayList).collect(Collectors.toList());
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {

        Preconditions.checkState(!(hasError && parsePolicy.failFast()), "解析上传文件发送错误,请检查");
        hasError = true;
        //数据错误
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException e = (ExcelDataConvertException) exception;
            ValidateResult validate = ofFailed(String.format("【%s】数据格式错误,请检查上传使用模版是否正确", e.getCellData()));
            illegalRows.put(e.getRowIndex(), new IllegalRow<>(validate, null));
        }
        log.error("exception:{}", "aaa", exception);
    }

    /**
     * 是否有错
     *
     * @return
     */
    public boolean hasError() {
        return hasError || illegalRows.isEmpty();
    }


}

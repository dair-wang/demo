package com.dair.demo.excel.validate;

import com.dair.demo.excel.annotation.ParsePolicy;
import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.filter.BaseFilter;

import java.util.Map;

public class ValidateExample implements IDataValidate {
    @Override
    public Map<Integer, IllegalRow> insertValidate(Map rows, ParsePolicy parsePolicy, WeightContext context) {
        return null;
    }

    @Override
    public Object insertTransform(Base row, BaseFilter filter, WeightContext context) {
        return null;
    }

    @Override
    public Object updateTransform(Base row, BaseFilter filter, WeightContext context) {
        return null;
    }

    @Override
    public Map<Integer, IllegalRow> updateValidate(Map rows, ParsePolicy parsePolicy, WeightContext context) {
        return null;
    }
}

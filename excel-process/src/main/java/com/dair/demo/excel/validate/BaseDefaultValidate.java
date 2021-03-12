package com.dair.demo.excel.validate;

import com.dair.demo.excel.annotation.ParsePolicy;
import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.filter.BaseFilter;

import java.util.Map;

public abstract class BaseDefaultValidate<E extends Base, U extends BaseFilter> implements
    IDataValidate<E, E, U> {

    @Override
    public Map<Integer, IllegalRow<E>> insertValidate(Map<Integer, E> rows, ParsePolicy parsePolicy,
        WeightContext context) {

        throw new UnsupportedOperationException();
    }

    @Override
    public Object insertTransform(E row, U filter, WeightContext context) {

        return null;
    }

    @Override
    public Map<Integer, IllegalRow<E>> updateValidate(Map<Integer, E> rows, ParsePolicy parsePolicy,
        WeightContext context) {

        throw new UnsupportedOperationException();
    }

    @Override
    public Object updateTransform(E row, U filter, WeightContext context) {

        return null;
    }
}

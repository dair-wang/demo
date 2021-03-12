package com.dair.demo.excel.validate;

import com.dair.demo.excel.annotation.ParsePolicy;
import com.dair.demo.excel.filter.Base;
import com.dair.demo.excel.filter.BaseFilter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @description: TODO
 * @author: gengzhenhua
 * @date : 2020/10/26 11:40 上午
 **/
public interface IDataValidate<E extends Base, F extends Base, U extends BaseFilter> {

    /**
     * validate rows
     *
     * @param rows        row validate success data entity
     * @param parsePolicy parse policy
     * @param context     transform context （maybe global data
     * @return validate result Map<line, IllegalRow<T>>
     */
    Map<Integer, IllegalRow<E>> insertValidate(Map<Integer, E> rows, ParsePolicy parsePolicy, WeightContext context);

    /**
     * validate row
     *
     * @param row upload data entity
     * @return validate result
     */
    default ValidateResult insertValidate(E row) {

        return DataValidateHelper.validateEntity(row);
    }

    /**
     * row trans to po
     *
     * @param row     upload data entity
     * @param filter  supported attr
     * @param context transform context （maybe global data）
     * @return trans result
     */
    Object insertTransform(E row, U filter, WeightContext context);

    /**
     * rows trans to po
     *
     * @param rows    upload data entity
     * @param filter  supported attr
     * @param context transform context （maybe global data）
     * @return trans result
     */
    default List<Object> insertTransform(Collection<E> rows, U filter, WeightContext context) {

        return rows.stream().map(r -> insertTransform(r, filter, context)).collect(toList());
    }

    /**
     * delete validate
     *
     * @param deleteFilter delete filter
     * @return validate result
     */
    default ValidateResult deleteValidate(U deleteFilter) {

        return ValidateResult.SUCCEED_RESULT;
    }

    /**
     * validate rows
     *
     * @param rows        row validate success data entity
     * @param parsePolicy parse policy
     * @param context     transform context （maybe global data
     * @return validate result Map<line, IllegalRow<T>>
     */
    Map<Integer, IllegalRow<F>> updateValidate(Map<Integer, F> rows, ParsePolicy parsePolicy, WeightContext context);

    /**
     * validate row
     *
     * @param row upload data entity
     * @return validate result
     */
    default ValidateResult updateValidate(F row) {

        return DataValidateHelper.validateEntity(row);
    }

    /**
     * row trans to po
     *
     * @param row     upload data entity
     * @param filter  supported attr
     * @param context transform context （maybe global data）
     * @return trans result
     */
    Object updateTransform(F row, U filter, WeightContext context);

    /**
     * rows trans to po
     *
     * @param rows    upload data entity
     * @param filter  supported attr
     * @param context transform context （maybe global data）
     * @return trans result
     */
    default List<Object> updateTransform(Collection<F> rows, U filter, WeightContext context) {

        return rows.stream().map(r -> updateTransform(r, filter, context)).collect(toList());
    }

}

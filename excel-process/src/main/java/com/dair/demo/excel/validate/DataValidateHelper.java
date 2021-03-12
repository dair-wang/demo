package com.dair.demo.excel.validate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Component
public class DataValidateHelper implements BeanPostProcessor {

    /**
     * 校验器
     */
    private static Validator VALIDATOR;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Validator) {
            VALIDATOR = (Validator) bean;
        }
        return bean;
    }

    /**
     * 校验实体
     *
     * @param obj 需要校验的实体
     * @param <T> 实体泛型
     * @return 校验结果
     */
    public static <T> ValidateResult validateEntity(T obj) {

        Set<ConstraintViolation<T>> validate = VALIDATOR.validate(obj, Default.class);
        String result = validate.stream().map(ConstraintViolation::getMessage).collect(joining(";"));
        return result.length() > 0 ? ValidateResult.ofFailed(result) : ValidateResult.SUCCEED_RESULT;
    }

}

package com.dair.demo.excel.validate;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * @description: 校验结果
 * @author: wanghaili
 * @date : 2020/11/11 3:30 下午
 **/
@Getter
@ToString
public class ValidateResult {

    /**
     * 校验结果
     */
    private final boolean success;
    /**
     * 校验失败提示信息
     */
    private final String message;

    /**
     * fixed success validation result
     */
    public static final ValidateResult SUCCEED_RESULT = new ValidateResult(true, null);

    /**
     * constructor
     *
     * @param success 校验结果
     * @param message 校验失败提示信息
     */
    public ValidateResult(boolean success, String message) {

        this.success = success;
        this.message = message;
    }

    /**
     * build success validation result
     *
     * @return 成功的结果
     */
    public static ValidateResult ofSuccess() {

        return new ValidateResult(true, null);
    }

    /**
     * build failure validation result
     *
     * @param message 校验失败提示信息
     * @return 失败的结果
     */
    public static ValidateResult ofSuccess(String message) {

        return new ValidateResult(true, message);
    }

    /**
     * build failure validation result
     *
     * @param message 校验失败提示信息
     * @return 失败的结果
     */
    public static ValidateResult ofFailed(String message) {

        return new ValidateResult(false, message);
    }

    /**
     * 合并失败原因
     *
     * @param validateResults 解析结果
     * @return 失败原因
     */
    public static ValidateResult mergeFailure(List<ValidateResult> validateResults) {

        String failMessage = validateResults.stream().filter(Objects::nonNull).filter(v -> !v.isSuccess())
            .map(ValidateResult::getMessage).collect(joining(","));
        return failMessage.length() > 0 ? new ValidateResult(false, failMessage)
            : SUCCEED_RESULT;
    }

}

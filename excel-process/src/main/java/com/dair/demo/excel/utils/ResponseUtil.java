package com.dair.demo.excel.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/*
 * @description: todo
 * @author wanghaili18
 * @date: 21-1-27 上午11:34
 */
public class ResponseUtil {


    /**
     * 设置返回头
     *
     * @param res      response
     * @param req      request
     * @param fileName 文件名称
     * @throws Exception
     */
    public static void renderResponseHeader(HttpServletResponse res,
                                            HttpServletRequest req,
                                            String fileName) throws Exception {

        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        res.setContentType("application/octet-stream");
        UserAgent agent = UserAgentUtil.parse(req.getHeader("User-Agent"));
        String name = agent.getBrowser().getName();
        String disposition;
        String newName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        switch (name) {
            case "opera":
            case "mozilla":
                disposition = "filename*=UTF-8''" + newName;
                break;
            case "safari":
                disposition = "filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + "\"";
                break;
            default:
                disposition = "filename=\"" + newName + "\"";
                break;
        }
        res.setHeader("Content-Disposition", "attachment; " + disposition);
    }
}

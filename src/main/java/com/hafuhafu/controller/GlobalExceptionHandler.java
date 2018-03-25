package com.hafuhafu.controller;

import com.hafuhafu.model.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-22
 * Time: 17:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo<String> errorHandler(HttpServletRequest request, Exception e) throws Exception {
        log.error("统一异常：{}",e.getMessage(),e);
        ResultInfo<String> result = new ResultInfo<>();
        result.setCode(ResultInfo.ERROR);
        result.setMessage("发生异常");
        result.setData("");
        result.setUrl(request.getRequestURL().toString());
        return result;

    }
}

package com.hafuhafu.model;

/**
 * Created with Intellij IDEA.
 * Description:
 * Author: stone
 * Date: 2018-03-22
 * Time: 17:39
 */
public class ResultInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = -1;

    private Integer code;
    private String message;
    private String url;
    private T data;

    public ResultInfo() {

    }

    public ResultInfo(Integer code,String message,String url,T data){
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

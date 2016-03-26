package com.charjack.bohe.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14.
 */
public class WebPassageInfo implements Serializable {
    private String code = "1";
    private PagerPassageInfo data;
    private String message ="1";

    public WebPassageInfo(){}

    public WebPassageInfo(PagerPassageInfo data){
        this.data =data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public PagerPassageInfo getData() {
        return data;
    }

    public void setData(PagerPassageInfo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

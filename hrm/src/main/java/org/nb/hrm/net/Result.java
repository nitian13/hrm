package org.nb.hrm.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Result {
    public static JSONObject StringToJson(String s){
        return JSON.parseObject(s);
    }
}

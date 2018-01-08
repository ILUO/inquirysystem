package com.gluxen.util;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.util.constants.Constants;
import com.gluxen.util.constants.ErrorEnum;
import com.gluxen.config.exception.CommonJsonException;
/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
public class CommonUtil {

    /**
     *返回一个returnData为空对象的成功消息的json
     *
     * @return
     */
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    /**
     *返回一个返回码为200的json
     *
     * @param returnData
     * @return
     */
    public static JSONObject successJson(Object returnData) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("returnCode", Constants.SUCCESS_CODE);
        resultJson.put("returnMsg", Constants.SUCCESS_MSG);
        resultJson.put("returnData", returnData);
        return resultJson;
    }

    /**
     * 返回错误信息JSON
     *
     * @param errorEnum 错误码的errorEnum
     * @return
     */
    public static JSONObject errorJson(ErrorEnum errorEnum) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("returnCode", errorEnum.getErrorCode());
        resultJson.put("returnMsg", errorEnum.getErrorMsg());
        resultJson.put("returnData", new JSONObject());
        return resultJson;
    }

    /**
     * 验证是否含有全部必填字段
     *
     * @param requiredColumns 必填的参数字段名称 逗号隔开 比如"userId,name,telephone"
     * @param jsonObject
     * @return
     */
    public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
        if (!StringTools.isNullOrEmpty(requiredColumns)) {
            //验证字段非空
            String[] columns = requiredColumns.split(",");
            String missCol = "";
            for (String column : columns) {
                Object val = jsonObject.get(column.trim());
                if (StringTools.isNullOrEmpty(val)) {
                    missCol += column + "  ";
                }
            }
            if (!StringTools.isNullOrEmpty(missCol)) {
                jsonObject.clear();
                jsonObject.put("returnCode", ErrorEnum.E_90003.getErrorCode());
                jsonObject.put("returnMsg", "缺少必填参数:" + missCol.trim());
                jsonObject.put("returnData", new JSONObject());
                throw new CommonJsonException(jsonObject);
            }
        }
    }
}

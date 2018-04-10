package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
public interface InspectionService {
    /**
     * 增加/更新检查指标
     * @param jsonObject
     * @return
     */
    JSONObject addInspectionIndex(JSONObject jsonObject);


    /**
     * 获取病人检查指标
     * @param jsonObject
     * @return
     */
    JSONObject getInspectionIndex(JSONObject jsonObject);


    /**
     * 删除病人检查指标
     * @param indexId
     * @return
     */
    JSONObject deleteInspection(int indexId);
}

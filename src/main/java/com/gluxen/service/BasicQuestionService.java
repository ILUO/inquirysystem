package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/1/11.
 */
public interface BasicQuestionService {
    /**
     * 获取基本问题信息
     * @return
     */
    JSONObject getBasicQuestion(Long patientId);

    /**
     * 新增/更新基本问题信息
     * @param jsonObject
     * @return
     */
    JSONObject addBasicQuestion(JSONObject jsonObject);
}

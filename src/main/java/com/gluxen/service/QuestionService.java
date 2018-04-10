package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/1/21.
 */
public interface QuestionService {
    /**
     * 增加/更新病人填空题历史
     * @param jsonObject
     * @return
     */
    JSONObject addHistory(JSONObject jsonObject);

    /**
     * 获得病人最近一次填空题历史
     * @param patientId
     * @return
     */
    JSONObject getCompletionHistory(Long patientId);

    /**
     * 获取病人最近一次选择题历史
     * @param patientId
     * @return
     */
    JSONObject getChoiceHistory(Long patientId);

    /**
     * 获取某次问诊的选择题历史
     * @param inquiryId
     * @return
     */
    JSONObject getInquiryChoice(int inquiryId);

    /**
     * 获取某次问诊的填空题历史
     * @param inquiryId
     * @return
     */
    JSONObject getInquiryCompletion(int inquiryId);

    /**
     * 获取最近的问诊答案
     * @param inquiryId
     * @return
     */
    JSONObject getAnswer(int inquiryId);
}

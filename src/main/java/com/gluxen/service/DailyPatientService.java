package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/14.
 */
public interface DailyPatientService {
    /**
     * 添加每日病人
     * @param jsonObject
     * @return
     */
    JSONObject addDailyPatient(JSONObject jsonObject);

    /**
     * 获取每日病人
     * @param jsonObject
     * @return
     */
    JSONObject getDailyPatient(JSONObject jsonObject);

    /**
     * 获取问诊情况
     * @param jsonObject
     * @return
     */
    JSONObject getDailyReport(JSONObject jsonObject);

    /**
     * 获取看病人数和人次
     * @return
     */
    JSONObject getPatientNumTime();

    /**
     * 获取第几次问诊和问诊类别
     * @return
     */
    JSONObject getInquiryInfo(Long inquiryId);

    /**
     * 更新状态，问诊Id
     * @param inquiryId
     */
    JSONObject updateStatus(Long inquiryId);
}

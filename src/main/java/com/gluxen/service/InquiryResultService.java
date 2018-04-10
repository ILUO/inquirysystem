package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
public interface InquiryResultService {
    /**
     * 添加问诊结果描述
     * @return
     */

    JSONObject addInquiryResult(int inquiryId);

    /**
     * 获取某个病人的问诊结果
     * @return
     */
    JSONObject getResultList(JSONObject jsonObject);

    /**
     * 获取
     * @param inquiryId
     * @return
     */
    JSONObject getOneResult(int inquiryId);
}

package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
public interface InquiryRecordService {

    /**
     * 获取问诊记录列表
     * @param jsonObject
     * @return
     */
    JSONObject getInquiryRecord(JSONObject jsonObject);
}

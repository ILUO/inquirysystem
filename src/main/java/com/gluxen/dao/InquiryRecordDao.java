package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
@Repository
public interface InquiryRecordDao {
    /**
     * 获取问诊记录列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getMedicalRecordList(JSONObject jsonObject);

    /**
     * 获取问诊记录数目
     * @param jsonObject
     * @return
     */
    int countMedicalRecord(JSONObject jsonObject);

    /**
     * 获取结果列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getResultList(JSONObject jsonObject);

    /**
     * 获取问诊结果数量
     * @param jsonObject
     * @return
     */
    int countResult(JSONObject jsonObject);

    /**
     * 获取某次问诊结果描述
     * @param inquiryId
     * @return
     */
    String getOneResult(int inquiryId);

    /**
     * 获取问诊Id列表
     * @param jsonObject
     * @return
     */
    List<Integer> getInquiryIdList(JSONObject jsonObject);

}

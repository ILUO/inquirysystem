package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/14.
 */
@Repository
public interface DailyPatientDao {
    /**
     * 获取当日预约病人
     * @param jsonObject
     * @return
     */
    List<JSONObject> getdailyPatientList(JSONObject jsonObject);

    /**
     * 添加当日预约病人
     * @param jsonObject
     */
    void addDailyPatient(JSONObject jsonObject);

    /**
     * 获取当人预约病人人数
     * @param jsonObject
     * @return
     */
    int countDailyPatient(JSONObject jsonObject);

    /**
     * 获取病人最近一次的问诊ID
     * @param patientId
     * @return
     */
    int getLatestInquiry(Long patientId);

    /**
     * 获取病人问诊情况
     * @param jsonObject
     * @return
     */
    List<JSONObject> getDailyReport(JSONObject jsonObject);

    /**
     * 获取问诊记录列表计数
     * @param jsonObject
     * @return
     */
    List<JSONObject> countDailyReport(JSONObject jsonObject);

    /**
     * 获取看病人数
     * @return
     */
    int getPatientNumber();

    /**
     * 获取看病人次
     * @return
     */
    int getPatientTime();

    /**
     * 根据问诊Id获取病人Id
     * @param inquiryId
     * @return
     */
    Long getPatientId(int inquiryId);

    /**
     * 获取问诊日期
     * @param inquiryId
     * @return
     */
    JSONObject getInquiry(int inquiryId);

    /**
     * 生成问诊结果
     * @param jsonObject
     */
    void putInquiryResult(JSONObject jsonObject);

    /**
     * 获取病人第几次问诊
     * @param jsonObject
     * @return
     */
    int getInquiryTimes(JSONObject jsonObject);

    /**
     * 获取问诊类型和第几次问诊
     * @param inquiryId
     * @return
     */
    JSONObject getInquiryTT(Long inquiryId);


    /**
     * 更新问诊状态
     */
    void updateStatus(JSONObject jsonObject);

    /**
     * 获取今日问诊人次
     * @param jsonObject
     * @return
     */
    Integer countToday(JSONObject jsonObject);
}

package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
@Repository
public interface PatientDao {
    /**
     * 查询病人基本信息
     * 在病人报到/就诊的时候要使用此方法
     *
     * @return
     */
    JSONObject getPatientInfo(Long patientId);

    /**
     * 生成新病人Id
     *
     * @return
     */
    int getNewPid();

    /**
     * 新增/更新病人基本信息
     *
     * @param jsonObject
     * @return
     */
    int addPatient(JSONObject jsonObject);

    /**
     * 新增/更新病人基本病史列表（基本填空题）
     *
     * @param object
     */
    void addBasicHistory(Object object);

    /**
     * 新增/更新病人家属列表
     * @param object
     */
    void addFamily(Object object);

    /**
     * 获取病人家属列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getFamily(JSONObject jsonObject);

    /**
     * 获取病人家属总数
     * @param jsonObject
     * @return
     */
    int countFamily(JSONObject jsonObject);

    /**
     * 获取病人基本病史（病史列表）
     * @param jsonObject
     * @return
     */
    List<JSONObject> getHistoryList(JSONObject jsonObject);

    /**
     * 获取病史，不分页
     * @param patientId
     * @return
     */
    List<JSONObject> getHistory(Long patientId);

    /**
     * 统计病史总数（基本填空题）
     * @param jsonObject
     * @return
     */
    int countHistory(JSONObject jsonObject);

    /**
     * 删除基本病史
     * @param basiccompletionId
     */
    void deleteHistory(int basiccompletionId);

    /**
     * 获取病人列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPatientList(JSONObject jsonObject);

    /**
     * 统计病人数量
     * @param jsonObject
     * @return
     */
    int countPatient(JSONObject jsonObject);

    /**
     * 删除病人家属
     * @param memberId
     */
    void deleteFamily(int memberId);


    /**
     * 删除病人
     * @param patientId
     */
    void deletPatient(int patientId);

    /**
     * 获取今日注册病人数量
     * @param createDate
     * @return
     */
    int getTodayNo(String createDate);

}

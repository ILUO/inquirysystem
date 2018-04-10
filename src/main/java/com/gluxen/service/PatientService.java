package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
public interface PatientService {
    /**
     * 获取病人基本信息
     *
     * @param patientId
     * @return
     */
    JSONObject getPatientInfo(Long patientId);

    /**
     * 获取新增病人Id
     *
     * @return
     */
    JSONObject getNewPid();


    /**
     * 获取病人家属
     * @param jsonObject
     * @return
     */
    JSONObject getFamily(JSONObject jsonObject);


    /**
     * 新增/更新病人基本信息、基本病史、家属
     * @param basicInfo
     * @param basicHistory
     * @param family
     * @return
     */
    JSONObject addPatientBasic(JSONObject basicInfo,JSONObject basicHistory,JSONObject family,JSONObject basicQuestion);

    /**
     * 获取病人基本病史列表
     * @param jsonObject
     * @return
     */
    JSONObject getBasicHistoryList(JSONObject jsonObject);

    /**
     * 删除基本病史
     * @param basiccompletionId
     * @return
     */
    JSONObject deletetHistory(int basiccompletionId);

    /**
     * 获取病人列表
     * @param jsonObject
     * @return
     */
    JSONObject getPatientList(JSONObject jsonObject);


    /**
     * 删除病人家属
     * @param memberId
     * @return
     */
    JSONObject deleteFamily(int memberId);

    /**
     * 获取病人基本信息文字描述
     * @param patientId
     * @return
     */
    JSONObject getDescription(Long patientId);

}

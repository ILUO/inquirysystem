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
    JSONObject getPatientInfo(int patientId);

    /**
     * 获取新增病人Id
     *
     * @return
     */
    JSONObject getNewPid();


    /**
     * 新增病人基本信息
     *
     * @param jsonObject
     * @return
     */
    JSONObject addPatient(JSONObject jsonObject);
}

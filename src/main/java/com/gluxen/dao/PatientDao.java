package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;


import java.util.List;

/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
public interface PatientDao {
    /**
     * 查询病人基本信息
     * 在病人报到/就诊的时候要使用此方法
     *
     * @return
     */
    JSONObject getPatientInfo(int patientId);

    /**
     * 生成新病人Id
     *
     * @return
     */
    int getNewPid();

    /**
     * 新增病人基本信息
     *
     * @param jsonObject
     * @return
     */
    int addPatient(JSONObject jsonObject);

}

package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.PatientService;
import com.gluxen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    /**
     * 获取病人的基本信息
     *
     * @param patientId
     * @return
     */
    @GetMapping("/getPatientInfo")
    public JSONObject getBasicInfo(@RequestParam int patientId){
        return patientService.getPatientInfo(patientId);
    }

    /**
     * 获取新增病人Id
     *
     * @return
     */
    @GetMapping("/getNewPid")
    public JSONObject getNewPid(){
        return patientService.getNewPid();
    }

    /**
     * 新增病人基本信息
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/addBasicInfo")
    public JSONObject addPatient(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"patientId,name,gender,certificatesType,certificatesNumber,birthday,importantDiagno");
        return patientService.addPatient(requestJson);
    }
}

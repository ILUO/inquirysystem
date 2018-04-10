package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.MedicalHistoryService;
import com.gluxen.util.CommonUtil;
import io.swagger.annotations.Api;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yang Xing Luo on 2018/1/30.
 */
@Api(value = "病历导出")
@RestController
@RequestMapping("/api/MedicalHistory")
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/downloadExcel")
    public JSONObject getBasicInfo(HttpServletRequest request, HttpServletResponse response){
        medicalHistoryService.downloadExcel(request,response);
        return CommonUtil.successJson();
    }
}

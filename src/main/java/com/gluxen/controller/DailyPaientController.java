package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.config.exception.CommonJsonException;
import com.gluxen.service.DailyPatientService;
import com.gluxen.service.impl.DailyPatientServiceImpl;
import com.gluxen.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yang Xing Luo on 2018/1/15.
 */
@Api(value = "每日病人")
@RestController
@RequestMapping("/api/dailyPatient")
public class DailyPaientController {
    @Autowired
    private DailyPatientService dailyPatientService;

    /**
     * 新增每日病人
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "添加每日就诊病人")
    @PostMapping("/addDailyPatient")
    public JSONObject addDailyPaient(@RequestBody JSONObject jsonObject){
        return dailyPatientService.addDailyPatient(jsonObject);
    }

    /**
     * 获取每日病人
     * @param pageNum
     * @param pageRow
     * @return
     */
    @ApiOperation(value = "获取每日就诊病人")
    @GetMapping("/getDailyPatient")
    public JSONObject getDailyPatient(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow){
        JSONObject request = new JSONObject();
        request.put("pageRow",pageRow);
        request.put("pageNum",pageNum);
        return dailyPatientService.getDailyPatient(request);
    }


    @ApiOperation(value = "获取病人问诊情况")
    @GetMapping("/getDailyReport")
    public JSONObject getDailyReport(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "开始日期") @RequestParam(value="startDate",required=false) String startDate,
            @ApiParam(value = "开始日期") @RequestParam(value="endDate",required=false) String endDate
    ){
        JSONObject request = new JSONObject();
        request.put("pageRow", pageRow);
        request.put("pageNum", pageNum);
        request.put("startDate", startDate);
        request.put("endDate",endDate);
        return dailyPatientService.getDailyReport(request);
    }

    @ApiOperation(value = "获取看病人数和人次")
    @GetMapping("/getPatientNumTime")
    public JSONObject getPatientNumTime(){
        return dailyPatientService.getPatientNumTime();
    }


    @ApiOperation(value = "获取问诊信息")
    @GetMapping("/getInquiryInfo")
    public JSONObject getInquiryInfo(
            @ApiParam(value = "问诊Id") @RequestParam(value="inquiryId",required=true) Long inquiryId){
        return dailyPatientService.getInquiryInfo(inquiryId);
    }

    @ApiOperation(value = "更新问诊状态")
    @GetMapping("/updateStatus")
    public JSONObject updateStatus(
            @ApiParam(value = "问诊Id") @RequestParam(value="inquiryId",required=true) Long inquiryId){
        return dailyPatientService.updateStatus(inquiryId);
    }
}

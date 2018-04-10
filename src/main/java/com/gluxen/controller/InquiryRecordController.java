package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.InquiryRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
@Api(value="问诊列表")
@RestController
@RequestMapping("/api/InquiryRecord")
public class InquiryRecordController {
    @Autowired
    private InquiryRecordService inquiryRecordService;

    @ApiOperation(value = "获取问诊列表")
    @GetMapping("/getDailyReport")
    public JSONObject getDailyReport(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "开始日期") @RequestParam(value="startDate",required=false) String startDate,
            @ApiParam(value = "开始日期") @RequestParam(value="endDate",required=false) String endDate,
            @ApiParam(value = "关键字") @RequestParam(value="keyString",required=false) String keyString,
            @ApiParam(value = "病人名字") @RequestParam(value = "patientName",required=false) String patientName
    ){
        JSONObject request = new JSONObject();
        request.put("pageRow", pageRow);
        request.put("pageNum", pageNum);

        request.put("startDate", startDate);


        request.put("endDate",endDate);


        request.put("keyString",keyString);


        request.put("patientName",patientName);

        return inquiryRecordService.getInquiryRecord(request);
    }
}

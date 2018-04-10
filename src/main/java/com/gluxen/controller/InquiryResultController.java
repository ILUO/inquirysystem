package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.InquiryResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
@Api(value = "生成问诊结果描述")
@RestController
@RequestMapping("/api/inquiryResult")
public class InquiryResultController {
    @Autowired
    private InquiryResultService inquiryResultService;

    @GetMapping("/putInquiryResult")
    public JSONObject putInquiryResult(@ApiParam(value = "问诊ID", required = true) @RequestParam("inquiryId") int inquiryId){
        return inquiryResultService.addInquiryResult(inquiryId);
    }

    @GetMapping("/getInquiryResultList")
    public JSONObject getInquiryResultList(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "病人Id",required = true) @RequestParam(value="patientId") Long patientId
    ){
        JSONObject request = new JSONObject();
        request.put("pageRow", pageRow);
        request.put("pageNum", pageNum);
        request.put("patientId",patientId);
        return inquiryResultService.getResultList(request);
    }

    /**
     * 获取某次问诊结果描述
     * @param inquiryId
     * @return
     */
    @GetMapping("/getOneResult")
    public JSONObject getOneResult(
            @ApiParam(value = "问诊Id", required = true) @RequestParam("inquiryId") int inquiryId
    ){
        return inquiryResultService.getOneResult(inquiryId);
    }
}

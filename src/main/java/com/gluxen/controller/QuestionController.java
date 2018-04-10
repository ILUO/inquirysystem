package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.DailyPatientDao;
import com.gluxen.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.sql.Types.NULL;

/**
 * Created by Yang Xing Luo on 2018/1/21.
 */
@Api(value = "问诊问题(历史)")
@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private DailyPatientDao dailyPatientDao;

    @ApiOperation(value = "获取最近一次的选择题(历史)")
    @GetMapping("/getChoiceHistory")
    public JSONObject getChoiceHistory(@ApiParam(value = "病人ID", required = true) @RequestParam Long patientId){
        return questionService.getChoiceHistory(patientId);
    }

    @ApiOperation(value = "获取最近一次的填空题(历史)")
    @GetMapping("/getCompletionHistory")
    public JSONObject getCompletionHistory(@ApiParam(value = "病人ID", required = true) @RequestParam Long patientId){
        return questionService.getCompletionHistory(patientId);
    }

    @ApiOperation(value = "获取某次问诊的选择题(历史)")
    @GetMapping("/getInquiryChoice")
    public JSONObject getInquiryChoice(@ApiParam(value = "问诊ID", required = true) @RequestParam int inquiryId){
        return questionService.getInquiryChoice(inquiryId);
    }

    @ApiOperation(value = "获取某次问诊的填空题(历史)")
    @GetMapping("/getInquiryCompletion")
    public JSONObject getInquiryCompletion(@ApiParam(value = "问诊ID", required = true) @RequestParam int inquiryId){
        return questionService.getInquiryCompletion(inquiryId);
    }

    @ApiOperation(value = "更改/新增病人问诊问题/历史")
    @PostMapping("/addHistory")
    public JSONObject addHistory(@RequestBody JSONObject jsonObject){
        return questionService.addHistory(jsonObject);
    }

    @ApiOperation(value = "获取最近病人选择题文字答案")
    @GetMapping("/getLatestAnswer")
    public  JSONObject getLatestAnswer(@ApiParam(value = "病人ID", required = true) @RequestParam Long patientId){
        int latestInquiryId = NULL;

        try {
            latestInquiryId = dailyPatientDao.getLatestInquiry(patientId);
        }catch (RuntimeException e){
            e.getMessage();
        }
        return questionService.getAnswer(latestInquiryId);
    }

    @ApiOperation(value = "获取某次病人选择题文字答案")
    @GetMapping("/getInquiryAnswer")
    public  JSONObject getInquiryAnswer(@ApiParam(value = "问诊ID", required = true) @RequestParam int inquiryId){
        return questionService.getAnswer(inquiryId);
    }
}

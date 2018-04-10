package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.BasicQuestionService;
import com.gluxen.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang Xing Luo on 2018/1/11.
 */
@Api(value="基本问诊选择题")
@RestController
@RequestMapping("/api/basicQuestion")
public class BasicQuestionController {
    @Autowired
    private BasicQuestionService basicQuestionService;

    /**
     * 获得病人基本问问诊选择题
     *
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取病人的基本选择题")
    @GetMapping("/getBasicQuestion")
    public JSONObject getBasicQuestion(@ApiParam(value = "病人ID", required = true) @RequestParam Long patientId){
        return basicQuestionService.getBasicQuestion(patientId);
    }

    /**
     * 新增病人基本问题
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "提交/更改病人基本选择题")
    @PostMapping("/addBasicQuestion")
    public JSONObject addBasicQuestion(@RequestBody JSONObject jsonObject){
        basicQuestionService.addBasicQuestion(jsonObject);
        return CommonUtil.successJson();
    }
}

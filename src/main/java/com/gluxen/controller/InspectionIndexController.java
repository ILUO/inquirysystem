package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.InspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
@Api(value = "检查指标")
@RestController
@RequestMapping("/api/inspectionIndex")
public class InspectionIndexController {
    @Autowired
    private InspectionService inspectionService;



    /**
     * 增加病人检查指标
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "新增/更改病人检查指标")
    @PostMapping("/addInspectionIndex")
    public JSONObject addInspectionIndex(@RequestBody JSONObject jsonObject){
        return inspectionService.addInspectionIndex(jsonObject);
    }

    /**
     * 获取病人检查指标
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取病人检查指标")
    @GetMapping("/getInspectionIndex")
    public JSONObject getInspectionIndex(
//            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
//            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "病人ID") @RequestParam(value="patientId",required=false) Long patientId
    ){
        JSONObject request = new JSONObject();
//        request.put("pageRow",pageRow);
//        request.put("pageNum",pageNum);
        request.put("patientId",patientId);
        return  inspectionService.getInspectionIndex(request);
    }

    /**
     * 删除检查指标
     * @param indexId
     * @return
     */
    @ApiOperation(value = "删除病人检查指标")
    @DeleteMapping("/deleteInspection")
    public JSONObject deletInspection(
            @ApiParam(value = "检查指标ID") @RequestParam(value="indexId") int indexId
    ){
        return inspectionService.deleteInspection(indexId);
    }

}

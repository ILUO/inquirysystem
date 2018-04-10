package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.PatientService;
import com.gluxen.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


/**
 * Created by Yang Xing Luo on 2017/12/31.
 */
@Api(value = "病人")
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    /**
     * 获取病人的基本信息
     *
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取病人基本信息")
    @GetMapping("/getPatientInfo")
    public JSONObject getBasicInfo(@ApiParam(value = "病人ID", required = true) @RequestParam Long patientId){
        return patientService.getPatientInfo(patientId);
    }



    /**
     * 获取新增病人Id
     *
     * @return
     */
    @ApiOperation(value = "获取新增病人的ID")
    @GetMapping("/getNewPid")
    public JSONObject getNewPid(){
        return patientService.getNewPid();
    }

    /**
     * 新增病人基本信息和基本病史、家人就诊情况
     *
     * @param basicJson
     * @return
     */
    @ApiOperation(value = "新增/更改病人基本信息+病史+家属")
    @PostMapping("/addBasicInfo")
    public JSONObject addPatient(@RequestBody JSONObject basicJson){

        JSONObject basicInfo = basicJson.getJSONObject("basicInfo");
        CommonUtil.hasAllRequired(basicInfo,"patientId,name,certificatesType,certificatesNumber,birthday");

        JSONObject basicHistory = basicJson.getJSONObject("basicHistory");

        JSONObject family = basicJson.getJSONObject("family");

        JSONObject basicQuestion = basicJson.getJSONObject("basicQuestion");

        return patientService.addPatientBasic(basicInfo,basicHistory,family,basicQuestion);
    }

    /**
     * 获取病人基本病史
     * @param pageNum
     * @param pageRow
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取病人基本病史")
    @GetMapping("/getHistoryList")
    public JSONObject getHistory(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "病人ID",required = true) @RequestParam(value="patientId") Long patientId){
        JSONObject request = new JSONObject();
        request.put("pageRow",pageRow);
        request.put("pageNum",pageNum);
        request.put("patientId",patientId);
        return  patientService.getBasicHistoryList(request);
    }


    /**
     * 获取病人列表
     * @param pageNum
     * @param pageRow
     * @param patientName
     * @return
     */
    @ApiOperation(value = "获取病人列表")
    @GetMapping(value = "/getPatientList")
    public JSONObject getPatientList(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "病人名字") @RequestParam(value="patientName",required=false) String patientName){
        JSONObject request = new JSONObject();
        request.put("pageRow",pageRow);
        request.put("pageNum",pageNum);
        request.put("patientName",patientName);
        return  patientService.getPatientList(request);
    }

    /**
     * 获取家属
     * @param pageNum
     * @param pageRow
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取病人家属")
    @GetMapping(value = "/getFamily")
    public JSONObject getFamily(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "病人ID") @RequestParam(value="patientId",required=false) Long patientId){
        JSONObject request = new JSONObject();
        request.put("pageRow",pageRow);
        request.put("pageNum",pageNum);
        request.put("patientId",patientId);
        return patientService.getFamily(request);
    }

    /**
     * 删除病人家属
     * @param memberId
     * @return
     */
    @ApiOperation(value = "删除病人家属")
    @DeleteMapping(value = "/deleteFamily")
    public JSONObject deleteFamily(
            @ApiParam(value = "成员Id",required = true) @RequestParam(value = "memberId") int memberId
    ){
        return  patientService.deleteFamily(memberId);
    }

    /**
     * 删除基本病史
     * @param basicCompletionId
     * @return
     */
    @ApiOperation(value = "删除基本病史")
    @DeleteMapping(value = "/deleteHistory")
    public JSONObject deletHistory(
            @ApiParam(value = "病史Id",required = true) @RequestParam(value = "basicCompletionId") int basicCompletionId
    ){
        return patientService.deletetHistory(basicCompletionId);
    }

    @ApiOperation(value = "获取病人基本信息文字描述")
    @GetMapping(value = "getDescription")
    public JSONObject getDescripetion(
            @ApiParam(value = "病人Id",required = true) @RequestParam(value = "patientId") Long patientId
    ){
        return patientService.getDescription(patientId);
    }
}


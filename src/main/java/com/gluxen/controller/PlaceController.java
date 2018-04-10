package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.PlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
@Api(value = "省市")
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    /**
     * 获取省份列表
     * @return
     */
    @ApiOperation(value = "获取省份列表")
    @GetMapping("/getProvList")
    public JSONObject getProvList(){
        return placeService.getProvince();
    }

    /**
     * 获取城市列表
     * @param provId
     * @return
     */
    @ApiOperation(value = "获取城市列表")
    @GetMapping("/getCityList")
    public JSONObject getCityList(@ApiParam(value = "省份Id",required = true) @RequestParam int provId){
        return placeService.getCity(provId);
    }

    /**
     * 获取城市和省份
     * @return
     */
    @ApiOperation(value = "获取城市和省份列表")
    @GetMapping("/getPlace")
    public JSONObject getPlaceList(){
        return placeService.getPlace();
    }
}

package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.PlaceDao;
import com.gluxen.service.PlaceService;
import com.gluxen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;

    /**
     * 获取省
     * @return
     */
    public JSONObject getProvince(){
        JSONObject returnData = new JSONObject();
        List<JSONObject> provList = placeDao.getProvince();
        returnData.put("provList",provList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 获取市
     * @param provId
     * @return
     */
    public JSONObject getCity(int provId){
        JSONObject returnData = new JSONObject();
        List<JSONObject> cityList = placeDao.getCity(provId);
        returnData.put("cityList",cityList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 同时获取城市和省份
     * @return
     */
    public JSONObject getPlace(){
        JSONObject returnData = new JSONObject();
        List<JSONObject> placeList = placeDao.getPlace();
        returnData.put("placeList",placeList);
        return CommonUtil.successJson(returnData);
    }

}

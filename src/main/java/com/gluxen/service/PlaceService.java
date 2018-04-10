package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
public interface PlaceService{
    /**
     * 获取省
     * @return
     */
    JSONObject getProvince();

    /**
     * 获取城市
     * @return
     */
    JSONObject getCity(int provId);


    /**
     * 同时获取省份和城市
     * @return
     */
    JSONObject getPlace();
}

package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
@Repository
public interface PlaceDao {
    /**
     * 获取省
     * @return
     */
    List<JSONObject> getProvince();

    /**
     * 获取城市
     * @return
     */
    List<JSONObject> getCity(int provId);


    /**
     * 同时获取城市和省份
     * @return
     */
    List<JSONObject> getPlace();

    /**
     * 获取城市名字
     * @param cityId
     * @return
     */
    String getCityName(String cityId);

    /**
     * 获取省份名字
     * @param provId
     * @return
     */
    String getProvName(String provId);
}

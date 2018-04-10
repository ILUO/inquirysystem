package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/18.
 */
@Repository
public interface InspectionIndexDao {
    /**
     * 增加检查指标
     */
    void addInspectionIndex(JSONObject jsonObject);

    /**
     * 获取病人检查指标
     * @param jsonObject
     */
    List<JSONObject> getInspectionIndex(JSONObject jsonObject);

    /**
     * 获取病人指标总数
     * @param jsonObject
     * @return
     */
    int countInspection(JSONObject jsonObject);

    /**
     * 删除检查指标
     * @param indexId
     */
    void deleteInspection(int indexId);
}

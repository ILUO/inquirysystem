package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/3/26.
 */
@Repository
public interface ExportDataDao {
    /**
     * 获取excel数据
     * @param jsonObject
     * @return
     */
    List<JSONObject> getExcelData(JSONObject jsonObject);

}

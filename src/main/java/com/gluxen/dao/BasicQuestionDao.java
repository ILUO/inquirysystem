package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/11.
 */
@Repository
public interface BasicQuestionDao {
    /**
     * 获得基本问题与选项
     * @return
     */
    List<JSONObject> getBasicQuestion(Long patientId);

    /**
     * 新增/更新病人基本选择题
     *
     * @param object
     */
    void addBasicQuestion(Object object);

    /**
     * 获取选项描述
     * @param choseId
     * @return
     */
    String getDescription(int choseId);
}

package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/16.
 */
@Repository
public interface ChoiceHistoryDao {

    /**
     * 获取病人选择题历史
     * @param latestInquiryId
     * @return
     */
    List<JSONObject> getChoiceHistory(int latestInquiryId);

    /**
     * 新增病人选择题历史
     * @param object
     */
    void addChoiceHistory(Object object);


    /**
     * 获取选项描述
     * @param choseId
     * @return
     */
    String getDescription(int choseId);

    /**
     * 获取病人选择答案
     * @param inquiryId
     * @return
     */
    List<JSONObject> getChoiceAnswer(int inquiryId);
}

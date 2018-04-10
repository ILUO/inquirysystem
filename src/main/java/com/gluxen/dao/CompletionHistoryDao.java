package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/17.
 */
@Repository
public interface CompletionHistoryDao {
    /**
     * 新增/更改病人填空题历史
     * @param object
     */
    void addCompletionHistory(Object object);


    /**
     * 获得病人填空题历史
     * @param latestInquiryId
     * @return
     */
    List<JSONObject> getCompletionHistory(int latestInquiryId);
}

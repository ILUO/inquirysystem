package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.BasicQuestionDao;
import com.gluxen.service.BasicQuestionService;
import com.gluxen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/11.
 */
@Service
public class BasicQuestionServiceImpl  implements BasicQuestionService {
    @Autowired
    private BasicQuestionDao basicQuestionDao;

    /**
     * 获取病人的基本问题
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getBasicQuestion(Long patientId){
        JSONObject returnData = new JSONObject();
        List<JSONObject> jsonObjectList = basicQuestionDao.getBasicQuestion(patientId);
        System.out.println("basicQuestion" + jsonObjectList.toString());
        returnData.put("questionList",jsonObjectList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 新增/更新病人基本问题
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject addBasicQuestion(JSONObject jsonObject){
        Object basicQuestionObject = jsonObject.get("basicQuestionList");
        basicQuestionDao.addBasicQuestion(basicQuestionObject);
        return CommonUtil.successJson();
    }
}


package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.InquiryRecordDao;
import com.gluxen.service.InquiryRecordService;
import com.gluxen.service.InquiryResultService;
import com.gluxen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
@Service
public class InquiryRecordServiceImpl implements InquiryRecordService {
    @Autowired
    private InquiryRecordDao inquiryRecordDao;
    /**
     * 获取问诊记录
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getInquiryRecord(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = inquiryRecordDao.countMedicalRecord(jsonObject);
        System.out.println(count);

        List<JSONObject> list = inquiryRecordDao.getMedicalRecordList(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }
}

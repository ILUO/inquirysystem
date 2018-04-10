package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.config.exception.CommonJsonException;
import com.gluxen.dao.DailyPatientDao;
import com.gluxen.service.DailyPatientService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/14.
 */
@Service
public class DailyPatientServiceImpl implements DailyPatientService{
    @Autowired
    private DailyPatientDao dailyPatientDao;

    /**
     * 新增每日病人
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject addDailyPatient(JSONObject jsonObject){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        jsonObject.put("inquiryDate",(df.format(new Date())).toString());
        Integer todayNum = dailyPatientDao.countToday(jsonObject);
        try{
            CommonUtil.hasAllRequired(jsonObject,"patientId,pname,inquiryType");
        }catch (CommonJsonException e){
            return e.getResultJson();
        }
        JSONObject returnData = new JSONObject();
        try{
            System.out.println(jsonObject.toString());
            int inquiryTimes = dailyPatientDao.getInquiryTimes(jsonObject);
            jsonObject.put("times",inquiryTimes+1);
            jsonObject.put("todayNum",todayNum+1);
            dailyPatientDao.addDailyPatient(jsonObject);
            System.out.println(jsonObject.toString());
            returnData.put("inquiryId",jsonObject.get("inquiryId"));
            return CommonUtil.successJson(returnData);
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90006);
        }
    }

    /**
     * 获取每日预约病人
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getDailyPatient(JSONObject jsonObject) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        jsonObject.put("inquiryDate",(df.format(new Date())).toString());
        CommonUtil.fillPageParam(jsonObject);
        int count = dailyPatientDao.countDailyPatient(jsonObject);
        List<JSONObject> list = dailyPatientDao.getdailyPatientList(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }


    /**
     * 获取病人问诊情况
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getDailyReport(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = dailyPatientDao.countDailyReport(jsonObject).size();
        List<JSONObject> list = dailyPatientDao.getDailyReport(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 获取看病人数和人次
     * @return
     */
    @Override
    public JSONObject getPatientNumTime(){
        int num = dailyPatientDao.getPatientNumber();
        int time = dailyPatientDao.getPatientTime();
        JSONObject returnData = new JSONObject();
        returnData.put("num",num);
        returnData.put("time",time);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 获取某次问诊信息
     * @return
     */
    @Override
    public JSONObject getInquiryInfo(Long inquiryId){
        JSONObject inquiryInfo = dailyPatientDao.getInquiryTT(inquiryId);
        return CommonUtil.successJson(inquiryInfo);
    }

    /**
     * 更新问诊状态
     * @param inquiryId
     * @return
     */
    @Override
    public JSONObject updateStatus(Long inquiryId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",1);
        jsonObject.put("inquiryId",inquiryId);
        dailyPatientDao.updateStatus(jsonObject);
        return CommonUtil.successJson();
    }
}

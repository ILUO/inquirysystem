package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.*;
import com.gluxen.service.InquiryResultService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.RowName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Yang Xing Luo on 2018/2/1.
 */
@Service
public class InquiryResultServiceImpl implements InquiryResultService {
    @Autowired
    private BasicQuestionDao basicQuestionDao;
    @Autowired
    private DailyPatientDao dailyPatientDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private CompletionHistoryDao completionHistoryDao;
    @Autowired
    private ChoiceHistoryDao choiceHistoryDao;
    @Autowired
    private InquiryRecordDao inquiryRecordDao;

    @Override
    public JSONObject addInquiryResult(int inquiryId){

//        RowName r = new RowName();
//        String[] rowList = r.getRowList();
//
//
//        Object[] objs = null;
//
//
//
//        JSONObject cellValue = r.getRowName();
//        try {
            Long patientId = dailyPatientDao.getPatientId(inquiryId);
//
//            List<JSONObject> basicQuestionList = basicQuestionDao.getBasicQuestion(patientId);
            JSONObject basicInfo = patientDao.getPatientInfo(patientId);
            List<JSONObject> completionHistory = completionHistoryDao.getCompletionHistory(inquiryId);
//            List<JSONObject> choiceHistory = choiceHistoryDao.getChoiceHistory(inquiryId);
//            List<JSONObject> history = patientDao.getHistory(patientId);
//            JSONObject inquiry = dailyPatientDao.getInquiry(inquiryId);
//
//            /**
//             * 填充基本信息
//             */
//            Set<String> basicInfoKey = basicInfo.keySet();
//            for(String key:basicInfoKey){
//                cellValue.replace(key,basicInfo.get(key));
//            }
//
//
//            /**
//             * 填充问诊信息
//             */
//            Set<String> inquiryKey = inquiry.keySet();
//            for(String key:inquiryKey){
//                cellValue.replace(key,inquiry.get(key));
//            }
//
//            /**
//             * 填充基本选择题
//             */
//            for(JSONObject basicQuestion : basicQuestionList){
//                JSONArray hisoryArray = basicQuestion.getJSONArray("historyList");
//                String stem = basicQuestion.getString("stem");
//                String remark = "";
//                String description = "";
//
//                int count = 0;
//                for (Iterator iterator = hisoryArray.iterator(); iterator.hasNext();) {
//                    JSONObject jsonObject = (JSONObject) iterator.next();
//                    String choseId = jsonObject.getString("choseId");
//                    if(count==0){
//                        description = description + basicQuestionDao.getDescription(Integer.valueOf(choseId));
//                    }else {
//                        description =   description + "," + basicQuestionDao.getDescription(Integer.valueOf(choseId));
//                    }
//                    if(count==0){
//                        remark = remark + jsonObject.getString("remark");
//                    }
//                    count++;
//                }
//                String result = "";
//                if(description!=null && description.length()!=0 ){
//                    result = result + description;
//                }
//                if(!remark.equals("null") && remark.length()!=0 ){
//                    result = result + "," + "备注:" + remark;
//                }
//                if(result !=null && result.length()!=0 ){
//                    cellValue.replace(stem,result);
//                }
//
//            }
//
//            /**
//             * 填充选择题
//             */
//            for(JSONObject choiceQuestion : choiceHistory){
//                JSONArray choiceQuestionArray = choiceQuestion.getJSONArray("choiceQuestionList");
//
//                for(Iterator iterator = choiceQuestionArray.iterator(); iterator.hasNext();){
//
//                    JSONObject questionTypeObject = (JSONObject) iterator.next();
//                    JSONArray jsonArray = questionTypeObject.getJSONArray("historyChoiceList");
//                    String stem = questionTypeObject.getString("stem");
//                    String remark = "";
//                    String description = "";
//                    if(jsonArray!=null){
//                        int count = 0;
//                        for (Iterator iterator2 = jsonArray.iterator(); iterator2.hasNext();) {
//                            JSONObject historyChoice = (JSONObject) iterator2.next();
//                            String choseId = historyChoice.getString("choseId");
//                            if(count==0){
//                                description = description + choiceHistoryDao.getDescription(Integer.valueOf(choseId));
//                                remark = remark + historyChoice.getString("remark");
//                            }else{
//                                description = description +  ","  + choiceHistoryDao.getDescription(Integer.valueOf(choseId));
//                            }
//                            count++;
//                        }
//                        String result = "";
//                        if(!description.equals(null) && description.length()!=0){
//                            result = result + description;
//                        }
//                        if(remark.length()!=0 && !remark.equals(null) && !remark.equals("null")){
//                            result = result + "," + "备注:" + remark;
//                        }
//                        if(result.length()!=0 && !result.equals(null)){
//                            cellValue.replace(stem,result);
//                        }
//                    }
//                }
//            }

            /**
             * 填充填空题
             */
//            for(JSONObject completionQuestion : completionHistory){
//                JSONArray compleyionQuestionArray = completionQuestion.getJSONArray("completionList");
//                for(Iterator iterator = compleyionQuestionArray.iterator();iterator.hasNext();){
//                    JSONObject completionTypeObject = (JSONObject) iterator.next();
//                    JSONArray jsonArray = completionTypeObject.getJSONArray("completionList");
//                    if(jsonArray!=null){
//                        for(Iterator iterator1 = jsonArray.iterator();iterator1.hasNext();){
//                            JSONObject historyCompletion = (JSONObject) iterator1.next();
//                            String stem = historyCompletion.getString("stem");
//                            String answer = historyCompletion.getJSONObject("historyCompletion").getString("answer");
//                            if(answer.length()!=0 && !answer.equals(null) && !answer.equals("null")){
//                                cellValue.replace(stem,answer);
//                            }
//                        }
//                    }
//                }
//            }
//
//            /**
//             * 填充基本病史
//             */
//            JSONObject historyRow = r.getHistoryRow();
//            for(JSONObject historyObject :history){
//                Set<String> keySet = historyObject.keySet();
//                for(String key:keySet){
//                    if(!historyObject.getString(key).equals("null") && historyObject.getString(key).length()!=0 && historyObject.getString(key)!=null)
//                        historyRow.replace(key,historyRow.getString(key)+historyObject.getString(key)+",");
//                }
//            }
//            Set<String> keySet = historyRow.keySet();
//            for(String key: keySet){
//                if(historyRow.getString(key)!=null){
//                    cellValue.replace(key,historyRow.getString(key));
//                }
//            }
//
//
//
//            objs = new Object[rowList.length];
//            for(int count=0 ;count < rowList.length ;count++){
//                objs[count] = cellValue.getString(rowList[count]);
//                if(objs[count]==null || objs[count].equals("") || objs[count].toString().length()==0){
//                    objs[count] = "null";
//                }
//            }
//
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
        String result = completionHistory.toString()+basicInfo.toString();
//        String[] resultKey = r.getRowList();
//        String[] chineseResultKey = r.chineseRowList();
//
//        for(int count=40;count<resultKey.length;count++){
//            if(!resultKey[count].equals(cellValue.getString(resultKey[count]))){
//                result = result + chineseResultKey[count] + ":" + cellValue.getString(resultKey[count]) + ";";
//            }
//        }

        System.out.println(result);
        JSONObject inquiryResult = new JSONObject();
        inquiryResult.put("inquiryId",inquiryId);
        inquiryResult.put("inquiryResult",result);
        dailyPatientDao.putInquiryResult(inquiryResult);
        return CommonUtil.successJson();
    }

    /**
     * 获取某个病人的查问诊结果描述列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getResultList(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = inquiryRecordDao.countResult(jsonObject);
        List<JSONObject> list = inquiryRecordDao.getResultList(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 获取某次问诊结果描述
     * @param inquiryId
     * @return
     */
    @Override
    public JSONObject getOneResult(int inquiryId){
        String result = inquiryRecordDao.getOneResult(inquiryId);
        JSONObject returnData = new JSONObject();
        returnData.put("result",result);
        return  CommonUtil.successJson(returnData);
    }
}

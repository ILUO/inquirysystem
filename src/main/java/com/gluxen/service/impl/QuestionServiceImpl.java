package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.ChoiceHistoryDao;
import com.gluxen.dao.CompletionHistoryDao;
import com.gluxen.dao.DailyPatientDao;
import com.gluxen.service.QuestionService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

/**
 * Created by Yang Xing Luo on 2018/1/21.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private ChoiceHistoryDao choiceHistoryDao;
    @Autowired
    private CompletionHistoryDao completionHistoryDao;
    @Autowired
    private DailyPatientDao dailyPatientDao;
    /**
     * 获取选择题病史
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getChoiceHistory(Long patientId){
        int latestInquiryId = NULL;
        try {
            latestInquiryId = dailyPatientDao.getLatestInquiry(patientId);
        }catch (RuntimeException e){
            e.getMessage();
        }
        JSONObject returnData = new JSONObject();
        List<JSONObject> choiceHistoryList = choiceHistoryDao.getChoiceHistory(latestInquiryId);
        returnData.put("choiceHistory",choiceHistoryList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 获取某次问诊的选择题历史
     * @param inquiryId
     * @return
     */
    @Override
    public JSONObject getInquiryChoice(int inquiryId){
        JSONObject returnData = new JSONObject();
        List<JSONObject> choiceHistoryList = choiceHistoryDao.getChoiceHistory(inquiryId);
        returnData.put("choiceHistory",choiceHistoryList);
        return CommonUtil.successJson(returnData);
    }


    /**
     * 获得填空题历史
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getCompletionHistory(Long patientId){
        int latestInquiryId = NULL;
        try {
            latestInquiryId = dailyPatientDao.getLatestInquiry(patientId);
        }catch (RuntimeException e){
            e.getMessage();
        }
        JSONObject returnData = new JSONObject();
        List<JSONObject> choiceHistoryList = completionHistoryDao.getCompletionHistory(latestInquiryId);
        returnData.put("completionHistory",choiceHistoryList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 获得某次问诊的填空题历史
     * @param inquiryId
     * @return
     */
    @Override
    public JSONObject getInquiryCompletion(int inquiryId){
        JSONObject returnData = new JSONObject();
        List<JSONObject> completionHistoryList = completionHistoryDao.getCompletionHistory(inquiryId);
//        for(int countCompletion=0;countCompletion<completionHistoryList.size();countCompletion++){
//            JSONObject completionType = completionHistoryList.get(countCompletion);
//            JSONArray completionArray = completionType.getJSONArray("completionList");
//            JSONArray
//        }
        returnData.put("completionHistory",completionHistoryList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 新增/更新病史
     * @param jsonObject
     * @return
     */
    @Override
//    @Transactional
    public JSONObject addHistory(JSONObject jsonObject){


        //System.out.println(jsonObject.toString());
        JSONArray questionTypeArray = jsonObject.getJSONArray("choiceHistory");
        JSONArray completionTypeArray = jsonObject.getJSONArray("completionHistory");
        String action = jsonObject.getString("action");


        //System.out.println(questionTypeArray.toString());

        String inquiryId = jsonObject.getString("inquiryId");
        List<JSONObject> choiceList = new ArrayList<JSONObject>();
        List<JSONObject> completionList = new ArrayList<JSONObject>();

        for(int countType = 0;countType < questionTypeArray.size();countType++){
            JSONObject questionType = questionTypeArray.getJSONObject(countType);

            //System.out.println(questionType);

            JSONArray choiceQuestionList = questionType.getJSONArray("choiceQuestionList");

           // System.out.println(choiceQuestionList.toString());

            for(int countQuestion = 0;countQuestion < choiceQuestionList.size();countQuestion++){
                JSONObject  question = choiceQuestionList.getJSONObject(countQuestion);
                //System.out.println(question.toString());

                String questionId = question.getString("questionId");

                JSONObject choiceHistory = question.getJSONObject("historyChoiceList");
                if(choiceHistory.size()!=0){
                    if(action.equals("0")){
                        choiceHistory.remove("historyId");
                        System.out.println("这是新增");
                    }
                    System.out.println(choiceHistory);
                    choiceHistory.put("questionId",questionId);
                    choiceHistory.put("inquiryId",inquiryId);
                    System.out.println(choiceHistory.toString()+"choiceHistory2");
                    choiceList.add(0,choiceHistory);
                }
            }
        }

        for(int countType=0;countType < completionTypeArray.size();countType++){
            JSONObject  questionType = completionTypeArray.getJSONObject(countType);
            JSONArray completionQusetionList = questionType.getJSONArray("completionList");

            for(int countQuestion = 0;countQuestion < completionQusetionList.size();countQuestion++){
                JSONObject question = completionQusetionList.getJSONObject(countQuestion);
                String questionId = question.getString("questionId");
                JSONObject completionHistory = question.getJSONObject("historyCompletion");
                if(action.equals("0")){
                    completionHistory.remove("completionId");
                    System.out.println("这是新增");
                }
                completionHistory.put("questionId",questionId);
                completionHistory.put("inquiryId",inquiryId);
                System.out.println(completionHistory.toString());
                completionList.add(0,completionHistory);
            }
        }
//        Object choiceHistoryObject = jsonObject.get("choiceHistoryList");
//        Object completionHistoryObject = jsonObject.get("completionHistoryList");
        try{
            System.out.println(choiceList.toString());
            choiceHistoryDao.addChoiceHistory(choiceList);
            completionHistoryDao.addCompletionHistory(completionList);
            System.out.println(completionList.toString()+"233333");
            return  CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonUtil.errorJson(ErrorEnum.E_90002);
        }
    }

    /**
     * 获取最近选择题答案
     * @param inquirytId
     * @return
     */
    @Override
    public JSONObject getAnswer(int inquirytId){

        JSONObject returnData = new JSONObject();

        List<JSONObject>  answerList = choiceHistoryDao.getChoiceAnswer(inquirytId);
        List<JSONObject> completionList = completionHistoryDao.getCompletionHistory(inquirytId);
        JSONObject questionAnswer = new JSONObject();

        questionAnswer.put("1",new JSONArray());
        questionAnswer.put("2",new JSONArray());
        questionAnswer.put("3",new JSONArray());
        questionAnswer.put("4",new JSONArray());
        questionAnswer.put("5",new JSONArray());
        questionAnswer.put("6",new JSONArray());

        for(int i=0;i < answerList.size();i++){
            JSONObject answer = answerList.get(i);

            String stem = answer.getString("stem");
            String remark = answer.getString("remark");
            String description = answer.getString("description");
            String questionTypeId = answer.getString("questionTypeId");
            questionTypeId = Integer.toString(Integer.parseInt(questionTypeId)+1);

            JSONArray jsonArray = questionAnswer.getJSONArray(questionTypeId);




            if(!description.equals("无异常") && !description.equals("否")){
                JSONObject choice = new JSONObject();
                if(remark!=null){
                    choice.put(stem,description+","+remark);
                }else{
                    choice.put(stem,description);
                }
                jsonArray.add(0,choice);
                questionAnswer.put(questionTypeId,jsonArray);
            }
            if((remark !=null && description.equals("无异常")) || (description.equals("否") && remark !=null )){
                JSONObject choice = new JSONObject();
                choice.put(stem,remark);
                jsonArray.add(0,choice);
                questionAnswer.put(questionTypeId,jsonArray);
            }
        }
        /**
         * 填空题
         */
        for(int i=0;i < completionList.size();i++){
            JSONObject completion = completionList.get(i);
            String questionTypeId = completion.getString("questionTypeId");
            if(questionTypeId.equals("6")){
                questionTypeId = "0";
            }
            questionTypeId = Integer.toString(Integer.parseInt(questionTypeId)+1);
            JSONArray jsonArray = questionAnswer.getJSONArray(questionTypeId);
            JSONArray completionArray = completion.getJSONArray("completionList");
            for(int j=0;j < completionArray.size();j++){
                JSONObject question = completionArray.getJSONObject(j);
                String stem = question.getString("stem");
                JSONObject answer = question.getJSONObject("historyCompletion");
                String result = answer.getString("answer");
                JSONObject description = new JSONObject();
                description.put(stem,result);
                jsonArray.add(0,description);
            }
            questionAnswer.put(questionTypeId,jsonArray);
        }


        returnData.put("questionAnswer",questionAnswer);
        return CommonUtil.successJson(returnData);
    }
}

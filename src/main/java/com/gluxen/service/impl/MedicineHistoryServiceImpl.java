package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.*;
import com.gluxen.service.MedicalHistoryService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/29.
 */
@Repository
public class MedicineHistoryServiceImpl implements MedicalHistoryService {
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
    private ExportDataDao exportDataDao;
    @Autowired
    private  InquiryRecordDao inquiryRecordDao;

    @Override
    public void downloadExcel(HttpServletRequest request,HttpServletResponse response){
        String keyString = request.getParameter("keyString");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyString",keyString);
        jsonObject.put("startDate",startDate);
        jsonObject.put("endDate",endDate);

//        List<Integer> inquiryIdList = inquiryRecordDao.getInquiryIdList(jsonObject);
//        for(int i=0;i<inquiryIdList.size();i++){
//            System.out.println(inquiryIdList.get(i));
//        }
        long startTime=System.currentTimeMillis();
        List<JSONObject> result = exportDataDao.getExcelData(jsonObject);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

//        String[] inquiryIdList = request.getParameterValues("inquiryId");
//        RowName r = new RowName();
//        String[] rowList = r.getRowList();
//
//        List<Object[]>  dataList = new ArrayList<Object[]>();
//        Object[] objs = null;
//
//
//        for(String attribute : inquiryIdList) {
//            JSONObject cellValue = r.getRowName();
//            try {
//                int inquiryId = Integer.parseInt(attribute);
//                Long patientId = dailyPatientDao.getPatientId(inquiryId);
//
//                JSONObject basicInfo = patientDao.getPatientInfo(patientId);
//                List<JSONObject> completionHistory = completionHistoryDao.getCompletionHistory(inquiryId);
//                List<JSONObject> choiceHistory = choiceHistoryDao.getChoiceHistory(inquiryId);
//
//                JSONObject inquiry = dailyPatientDao.getInquiry(inquiryId);
//
//                /**
//                 * 填充基本信息
//                 */
//                Set<String> basicInfoKey = basicInfo.keySet();
//                for(String key:basicInfoKey){
//                    cellValue.replace(key,basicInfo.get(key));
//                }
//
//                /**
//                 * cellValue的key与basicInfo的key需要一致
//                 */
//
//                /**
//                 * 填充问诊信息
//                 */
//                Set<String> inquiryKey = inquiry.keySet();
//                for(String key:inquiryKey){
//                    cellValue.replace(key,inquiry.get(key));
//                }

                /**
                 * cellValue的key与inquiry的key需要一致
                 */

//                /**
//                 * 填充基本选择题
//                 */
//                for(JSONObject basicQuestion : basicQuestionList){
//                    JSONArray hisoryArray = basicQuestion.getJSONArray("historyList");
//                    String stem = basicQuestion.getString("stem");
//                    String remark = "";
//                    String description = "";
//
//                    int count = 0;
//                    for (Iterator iterator = hisoryArray.iterator(); iterator.hasNext();) {
//                        JSONObject jsonObject = (JSONObject) iterator.next();
//                        String choseId = jsonObject.getString("choseId");
//                        if(count==0){
//                            description = description + basicQuestionDao.getDescription(Integer.valueOf(choseId));
//                        }else {
//                            description =   description + "," + basicQuestionDao.getDescription(Integer.valueOf(choseId));
//                        }
//                        if(count==0){
//                            remark = remark + jsonObject.getString("remark");
//                        }
//                        count++;
//                    }
//                    String result = "";
//                    if(description!=null && description.length()!=0 ){
//                        result = result + description;
//                    }
//                    if(!remark.equals("null") && remark.length()!=0 ){
//                        result = result + "," + remark;
//                    }
//                    if(result !=null && result.length()!=0 ){
//                        cellValue.replace(stem,result);
//                    }
//                }

                /**
                 * 填充选择题
                 */
//                for(JSONObject choiceQuestion : choiceHistory){
//                    JSONArray choiceQuestionArray = choiceQuestion.getJSONArray("choiceQuestionList");
//
//                    for(Iterator iterator = choiceQuestionArray.iterator(); iterator.hasNext();){
//
//                        JSONObject questionTypeObject = (JSONObject) iterator.next();
//                        JSONArray jsonArray = questionTypeObject.getJSONArray("historyChoiceList");
//                        String stem = questionTypeObject.getString("stem");
//                        String remark = "";
//                        String description = "";
//                        if(jsonArray!=null){
//                            int count = 0;
//                            for (Iterator iterator2 = jsonArray.iterator(); iterator2.hasNext();) {
//                                JSONObject historyChoice = (JSONObject) iterator2.next();
//                                String choseId = historyChoice.getString("choseId");
//                                if(count==0){
//                                    description = description + choiceHistoryDao.getDescription(Integer.valueOf(choseId));
//                                    remark = remark + historyChoice.getString("remark");
//                                }else{
//                                    description = description +  ","  + choiceHistoryDao.getDescription(Integer.valueOf(choseId));
//                                }
//
//                                count++;
//                            }
//                            String result = "";
//                            if(!description.equals(null) && description.length()!=0 && !description.equals("null")){
//                                result = result + description;
//                            }
//                            if(remark.length()!=0 && !remark.equals(null)&& !remark.equals("null")){
//                                result = result + ","  + remark;
//                            }
//                            if(result.length()!=0 && !result.equals(null)){
//                                cellValue.replace(stem,result);
//                            }
//                        }
//                    }
//                }
//
//                /**
//                 * cellValue的key与stem需要一致
//                 */
//
//
//                /**
//                 * 填充填空题
//                 */
//                 for(JSONObject completionQuestion : completionHistory){
//                     JSONArray compleyionQuestionArray = completionQuestion.getJSONArray("completionList");
//                     for(Iterator iterator = compleyionQuestionArray.iterator();iterator.hasNext();){
//                         JSONObject completionTypeObject = (JSONObject) iterator.next();
//                         JSONArray jsonArray = completionTypeObject.getJSONArray("completionList");
//                         if(jsonArray!=null){
//                             for(Iterator iterator1 = jsonArray.iterator();iterator1.hasNext();){
//                                 JSONObject historyCompletion = (JSONObject) iterator1.next();
//                                 String stem = historyCompletion.getString("stem");
//                                 String answer = historyCompletion.getJSONObject("historyCompletion").getString("answer");
//                                 if(answer.length()!=0 && !answer.equals(null) && !answer.equals("null")){
//                                     cellValue.replace(stem,answer);
//                                 }
//                             }
//                         }
//                     }
//                 }
                /**
                 * cellValue的key与stem需要一致
                 */

//                /**
//                 * 填充基本病史
//                 */
//                JSONObject historyRow = r.getHistoryRow();
//
//                 for(JSONObject historyObject :history){
//                     Set<String> keySet = historyObject.keySet();
//                     for(String key:keySet){
//                         if(!historyObject.getString(key).equals("null") && historyObject.getString(key).length()!=0 && historyObject.getString(key)!=null)
//                         historyRow.replace(key,historyRow.getString(key)+historyObject.getString(key)+",");
//                     }
//                 }
//                Set<String> keySet = historyRow.keySet();
//                for(String key: keySet){
//                    if(historyRow.getString(key)!=null){
//                        cellValue.replace(key,historyRow.getString(key));
//                    }
//                }



//                String[] chineseRowsName = r.chineseRowList();
//                objs = new Object[rowList.length];
//                for(int count=0 ;count < rowList.length ;count++){
//                    objs[count] = cellValue.getString(rowList[count]);
//                    if(objs[count]==null || objs[count].equals("") || objs[count].toString().length()==0 || chineseRowsName[count].equals(objs[count])){
//                        objs[count] = "null";
//                    }
//                }
//                System.out.println(objs[0]);
//                dataList.add(objs);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        String title = "问诊记录";
//        String[] rowsName = r.chineseRowList();
//        try {
//            String fileName = "问诊记录-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
//            CommonExcelUtil ex = new CommonExcelUtil(title, rowsName, dataList, response, fileName);
//            ex.downloadExcel();
//        }catch (Exception e){
//            e.printStackTrace();
        }
}


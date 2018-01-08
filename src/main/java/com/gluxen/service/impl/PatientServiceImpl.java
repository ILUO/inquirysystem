package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.PatientDao;
import com.gluxen.service.PatientService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Yang Xing Luo on 2017/12/31.
 */

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;

    /**
     * Sting字符串分割为List
     *
     * @param object
     * @param infoKey
     * @return
     */
    private JSONObject StringToList(JSONObject object,String infoKey){
        if(object.containsKey(infoKey)){
            String info = object.get(infoKey).toString();
            List<String> list = java.util.Arrays.asList(info.split("\\$"));
            object.put(infoKey,list);
        }
        return object;
    }

    /**
     * Arraylist<String>拼接为String
     *
     * @param jsonObject
     * @param infokey
     * @return
     */
    private JSONObject ListToString(JSONObject jsonObject,String infokey) {
        Object object = jsonObject.get(infokey);
        ArrayList array = (ArrayList)object;

        String[] stringArray = (String[]) array.toArray(new String[0]);

        List<String> list = Arrays.asList(stringArray);

        String str = StringUtils.collectionToDelimitedString(list, "$");

        jsonObject.put(infokey,str);

        return jsonObject;
    }

    /**
     * 获取病人基本信息
     *
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getPatientInfo(int patientId){
        JSONObject returnData = new JSONObject();
        JSONObject jsonObject = patientDao.getPatientInfo(patientId);


        List<String> listName = Arrays.asList("importantDiagno","residence","incunabulum","mainSymptoms","familyHistory","familyMembers","infectionHistory","anamensis","contactHistory","traumaHistory","surgeryHistory");

        for(String attribute : listName) {
            try {
                jsonObject = StringToList(jsonObject, attribute);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        returnData.put("patientInfo", jsonObject);


        return CommonUtil.successJson(returnData);
    }

    /**
     * 获取新增病人Id
     *
     * @return
     */
    @Override
    public  JSONObject getNewPid(){
        JSONObject returnData = new JSONObject();
        Integer newId = patientDao.getNewPid();
        returnData.put("patientId",newId);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 新增病人
     *
     * @param jsonObject
     * @return
     */
    @Override
    public  JSONObject addPatient(JSONObject jsonObject){
        JSONObject returnData = new JSONObject();

        List<String> listName = Arrays.asList("importantDiagno","residence","incunabulum","mainSymptoms","familyHistory","familyMembers","infectionHistory","anamensis","contactHistory","traumaHistory","surgeryHistory");
        for(String attribute : listName) {
            try{
                ListToString(jsonObject,attribute);
            }catch (NullPointerException e){

            }
        }
        try{
            patientDao.addPatient(jsonObject);
            returnData.put("patientId",jsonObject.get("patientId"));
            return CommonUtil.successJson(returnData);
        }catch (RuntimeException e){
            returnData.put("errorMessage",e.getMessage());
            return  CommonUtil.errorJson(ErrorEnum.E_90005);
        }
    }
}

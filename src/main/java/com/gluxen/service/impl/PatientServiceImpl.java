package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.BasicQuestionDao;
import com.gluxen.dao.PatientDao;
import com.gluxen.dao.PlaceDao;
import com.gluxen.service.PatientService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Yang Xing Luo on 2017/12/31.
 */

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private PlaceDao placeDao;

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
        JSONArray jsonArray = jsonObject.getJSONArray(infokey);

        if(jsonArray!=null){
            List<String> list = new ArrayList<String>();
            for (int i=0; i<jsonArray.size(); i++) {
                list.add(jsonArray.getString(i) );
            }
            String str = StringUtils.collectionToDelimitedString(list, "$");
            jsonObject.put(infokey,str);
        }
        return jsonObject;
    }

    /**
     * 获取病人基本信息
     *
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getPatientInfo(Long patientId){
        JSONObject returnData = new JSONObject();
        JSONObject basicInfo = patientDao.getPatientInfo(patientId);
        System.out.println(basicInfo.toString());
        List<String> listName = Arrays.asList("residence","incunabulum","contactHistory","eatingHabits","pastHistory","allergy","infectiousHistory","heredityHistory");


        for(String attribute : listName) {
            try {
                basicInfo = StringToList(basicInfo, attribute);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        returnData.put("patientInfo", basicInfo);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date()).toString();// new Date()为获取当前系统时间
//        Integer newId = patientDao.getNewPid();
        int todayNo = patientDao.getTodayNo(date.substring(0,10));
        String No = String.format("%05d",todayNo);

 //       date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10) + date.substring(11,13) + date.substring(14,16) + date.substring(17,19);
 //       String Id = date + newId.toString();
        date = date.substring(0,4) + date.substring(5,7) + date.substring(8,10);

        try{
            Long newId = Long.parseLong(date+No);
            returnData.put("patientId",newId);
            JSONObject patient = new JSONObject();
            patient.put("patientId",newId);
            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
            String createDate = sdf.format(new Date()).toString();
            patient.put("createDate",createDate);
            patientDao.addPatient(patient);
            return CommonUtil.successJson(returnData);
        }catch (Exception e){
            return  CommonUtil.errorJson(ErrorEnum.E_90019);
        }
    }

    /**
     * 获取病人家属列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getFamily(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = patientDao.countFamily(jsonObject);
        List<JSONObject> list = patientDao.getFamily(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }


    /**
     * 新增病人基本信息和基本病史、家人就诊情况
     * @param basicInfo
     * @param basicHistory
     * @param family
     * @return
     */
    @Override
//    @Transactional
    public JSONObject addPatientBasic(JSONObject basicInfo,JSONObject basicHistory,JSONObject family,JSONObject basicQuestion){
//        JSONObject inquiryObject = new JSONObject();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        inquiryObject.put("inquiryType",basicInfo.get("inquiryType"));
//        inquiryObject.put("patientId",basicInfo.get("patientId"));
//        inquiryObject.put("inquiryDate",(df.format(new Date())).toString());
//        inquiryObject.put("pname",basicInfo.get("name"));

        String birthday = basicInfo.getString("birthday");
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = sdf.parse(birthday);
            String createDate = sdf.format(new Date()).toString();// new Date()为获取当前系统时间
            int age = getAge(date);
            basicInfo.put("age",age);
            basicInfo.put("createDate",createDate);
            JSONArray residence = basicInfo.getJSONArray("residence");
            String city = placeDao.getProvName(residence.get(0).toString()) + placeDao.getCityName(residence.get(1).toString());
            basicInfo.put("city",city);
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject returnData = new JSONObject();
        Object historyObject = basicHistory.get("basicHistoryList");
        Object familyObject = family.get("familyList");
        //Object basicQuestionObject = basicQuestion.get("basicQuestionList");

//        JSONArray contactHistory = basicHistory.getJSONArray("contactHistory");
//        JSONArray eatingHabits = basicHistory.getJSONArray("eatingHabits");
//        JSONArray pastHistory = basicHistory.getJSONArray("pastHistory");



        List<String> listName = Arrays.asList("residence","incunabulum","contactHistory","eatingHabits","pastHistory","allergy","infectiousHistory","heredityHistory");
        for(String attribute : listName) {
            try{
                ListToString(basicInfo,attribute);
            }catch (NullPointerException e){
            }
        }

        try{
            System.out.println(basicInfo.toString());
            patientDao.addPatient(basicInfo);
            //basicQuestionDao.addBasicQuestion(basicQuestionObject);
            if(historyObject!=null&& !historyObject.toString().equals("[]")) {
                patientDao.addBasicHistory(historyObject);
            }
            if(familyObject!=null&& !familyObject.toString().equals("[]")){
                patientDao.addFamily(familyObject);
            }
            returnData.put("patientId",basicInfo.get("patientId"));
            return CommonUtil.successJson(returnData);
        }catch (Exception e){
            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonUtil.errorJson(ErrorEnum.E_90005);
        }
    }

    /**
     * 获取病人基本病史（接触史等）
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getBasicHistoryList(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = patientDao.countHistory(jsonObject);
        System.out.println(count);
        List<JSONObject> list = patientDao.getHistoryList(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 获取病人列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPatientList(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = patientDao.countPatient(jsonObject);
        List<JSONObject> list = patientDao.getPatientList(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 删除病人家属
     * @param memberId
     * @return
     */
    @Override
    public JSONObject deleteFamily(int memberId){
        try{
            patientDao.deleteFamily(memberId);
            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90011);
        }
    }

    /**
     * 删除病人基本病史
     * @param basiccompletionId
     * @return
     */
    @Override
    public JSONObject deletetHistory(int basiccompletionId){
        try{
            patientDao.deleteHistory(basiccompletionId);
            return CommonUtil.successJson();
        }catch (RuntimeException e) {
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90013);
        }
    }

    /**
     * 获取病人基本信息描述
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getDescription(Long patientId){
        JSONObject returnData = new JSONObject();
        JSONObject basicInfo = patientDao.getPatientInfo(patientId);
//        String result = "";
//        String birth = "";
//        try{
//            result = "姓名:" + basicInfo.getString("name") + "，年龄："+ basicInfo.getString("age")
//                             + "，性别："+ basicInfo.getString("gender") + "，来自：" + basicInfo.getString("city")
//                             +"，国籍："+ basicInfo.getString("nationality") + "，民族：" + basicInfo.getString("pnational")+"，身高：" + basicInfo.getString("height") +"cm" + "，体重：" + basicInfo.getString("weight")+"kg"
//                             + "，职业：" + basicInfo.getString("occupation") +"，输血史：" + basicInfo.get("bloodTrans") + "，吸烟：" + basicInfo.getString("smoke")
//                             + "，饮酒：" + basicInfo.getString("drink") + "，每日饮酒(两)：" + basicInfo.getString("dailyDrink") + "，每日吸烟(支)：" + basicInfo.getString("dailySmoke")
//                             + "，戒烟：" + basicInfo.get("quitSmoke") + "，戒酒：" + basicInfo.getString("quitDrink") + "，婚姻史：" + basicInfo.getString("marriage")
//                             + "，避孕：" + basicInfo.getString("contraception") + "，接触史："+ basicInfo.getString("contactHistory") + "，饮食习惯：" + basicInfo.getString("eatingHabits")
//                             + "，既往病史：" + basicInfo.getString("pastHistory");
//            if(basicInfo.getString("gender").equals("女")){
//                birth = birth + "，怀孕：" + basicInfo.getString("birth") + "，生育：" + basicInfo.getString("pregnant")
//                        + "，早产：" + basicInfo.getString("prematureLabour") + "，流产：" + basicInfo.getString("abortion");
//            }
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//
//        returnData.put("description",result+birth);
        List<String> listName = Arrays.asList("contactHistory","eatingHabits","pastHistory","allergy","infectiousHistory","heredityHistory");


        for(String attribute : listName) {
            try {
                if(basicInfo.containsKey(attribute)){
                    String info = basicInfo.get(attribute).toString();
                    basicInfo.put(attribute,info.replace("$",","));
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        String residence = basicInfo.getString("residence");
        List<String> residenceList = java.util.Arrays.asList(residence.split("\\$"));
        basicInfo.put("residence",placeDao.getProvName(residenceList.get(0))+""+placeDao.getCityName(residenceList.get(1)));

        String incunabulum = basicInfo.getString("incunabulum");
        List<String> incunabulumList = java.util.Arrays.asList(incunabulum.split("\\$"));
        basicInfo.put("incunabulum",placeDao.getProvName(incunabulumList.get(0))+""+placeDao.getCityName(incunabulumList.get(1)));

        JSONObject result = new JSONObject();
        result.put("姓名",basicInfo.getString("name"));
        result.put("民族",basicInfo.getString("national")+"族");
        result.put("身高体重",basicInfo.getString("height")+"cm/"+basicInfo.get("weight")+"kg");
        result.put("职业",basicInfo.getString("occupation"));
        result.put("婚姻",basicInfo.getString("marriage"));
        result.put("年龄",basicInfo.getString("age")+"岁");
        result.put("居住地",basicInfo.getString("incunabulum") + "/" + basicInfo.getString("residence"));

        result.put("性别",basicInfo.getString("gender"));

        if(basicInfo.getString("smoke").equals("是")){
            String resultString = "是，每日吸烟"+basicInfo.get("dailySmoke")+"支";
            if(basicInfo.getString("quitSmoke").equals("是")){
                resultString = resultString + "，已戒烟"+basicInfo.getString("quitSmokeTime");
            }
            result.put("吸烟史",resultString);
        }else{
            result.put("吸烟史","否");
        }

        if(basicInfo.getString("drink").equals("是")){
            String resultString = "是，每日饮酒"+basicInfo.get("dailyDrink")+"两";
            if(basicInfo.getString("quitDrink").equals("是")){
                resultString = resultString + "，已戒酒"+basicInfo.getString("quitDrinkTime");
            }
            result.put("饮酒史",resultString);
        }else{
            result.put("饮酒史","否");
        }

        if(basicInfo.getString("bloodTrans").equals("是")){
            if(basicInfo.containsKey("bloodTransRemark") && !basicInfo.getString("bloodTransRemark").equals("无")){
                result.put("输血史","是,"+basicInfo.getString("bloodTransRemark"));
            }else{
                result.put("输血史","是");
            }
        }else{
            result.put("输血史","否");
        }

        if(basicInfo.containsKey("operationHistory") && basicInfo.getString("operationHistory").length()!=0){
            result.put("手术史",basicInfo.getString("operationHistory"));
        }

        if(basicInfo.containsKey("familyHistory")){
            result.put("家族史",basicInfo.getString("familyHistory"));
        }

        if(basicInfo.containsKey("menarche")){
            String resultString = "初潮:" + basicInfo.getString("menarche") + ",周期:" + basicInfo.getString("menstrualCycle") + ",时长:" + basicInfo.getString("menstruationPeriod");
            if(basicInfo.containsKey("menopauseAge")){
                resultString = resultString + ",绝经年龄:" + basicInfo.getString("menopauseAge");
            }
            result.put("月经史",resultString);
        }

        if(basicInfo.containsKey("pregnant")&&basicInfo.containsKey("birth")&&basicInfo.containsKey("prematureLabour")&&basicInfo.containsKey("abortion")){
            result.put("生育史","孕"+basicInfo.getString("pregnant")+",产"+basicInfo.getString("birth")+",早产"+basicInfo.getString("prematureLabour")+",流产"+basicInfo.getString("abortion")+",避孕:"+basicInfo.getString("contraception"));
        }

        if(basicInfo.containsKey("infectiousHistory")){
            if(basicInfo.containsKey("infectiousHistoryRemark") && !basicInfo.getString("infectiousHistoryRemark").equals("无")){
                result.put("传染病史",basicInfo.getString("infectiousHistory")+","+basicInfo.getString("infectiousHistoryRemark"));
            }else{
                result.put("传染病史",basicInfo.getString("infectiousHistory"));
            }
        }
        if(basicInfo.containsKey("heredityHistory")){
            if(basicInfo.containsKey("heredityHistoryRemark") && !basicInfo.getString("heredityHistoryRemark").equals("无")){
                result.put("遗传病史",basicInfo.getString("heredityHistory")+","+basicInfo.getString("heredityHistoryRemark"));
            }else{
                result.put("遗传病史",basicInfo.getString("heredityHistory"));
            }
        }
        if(basicInfo.containsKey("traumaHistory")){
            result.put("外伤史",basicInfo.getString("traumaHistory"));
        }
        if(basicInfo.containsKey("operationHistory")){
            result.put("手术史",basicInfo.getString("operationHistory"));
        }
        if(basicInfo.containsKey("familyHistory")){
            result.put("家人病史",basicInfo.getString("familyHistory"));
        }
        if(basicInfo.containsKey("contactHistory")){
            if(basicInfo.containsKey("contactHistoryRemark") && !basicInfo.getString("contactHistoryRemark").equals("无")){
                result.put("接触史",basicInfo.getString("contactHistory")+","+basicInfo.getString("contactHistoryRemark"));
            }else{
                result.put("接触史",basicInfo.getString("contactHistory"));
            }
        }
        if(basicInfo.containsKey("drugAllergy")){
            if(basicInfo.containsKey("drugAllergyRemark") && !basicInfo.getString("drugAllergyRemark").equals("无")){
                result.put("药物过敏",basicInfo.getString("drugAllergy")+","+basicInfo.getString("drugAllergyRemark"));
            }else{
                result.put("药物过敏",basicInfo.getString("drugAllergy"));
            }
        }
        if(basicInfo.containsKey("allergy")){
            if(basicInfo.containsKey("allergyRemark") && !basicInfo.getString("allergyRemark").equals("无")){
                result.put("过敏物",basicInfo.getString("allergy")+","+basicInfo.getString("allergyRemark"));
            }else{
                result.put("过敏物",basicInfo.getString("allergy"));
            }
        }
        if(basicInfo.containsKey("belief")){
            result.put("宗教信仰",basicInfo.getString("belief"));
        }

        if(basicInfo.containsKey("eatingHabits")){
            if(basicInfo.containsKey("eatingHabitsRemark") && !basicInfo.getString("eatingHabitsRemark").equals("无")){
                result.put("特殊饮食习惯",basicInfo.getString("eatingHabits")+","+basicInfo.getString("eatingHabitsRemark"));
            }else{
                result.put("特殊饮食习惯",basicInfo.getString("eatingHabits"));
            }
        }

        returnData.put("patientInfo",result);
        return CommonUtil.successJson(returnData);
    }

    public int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }
}

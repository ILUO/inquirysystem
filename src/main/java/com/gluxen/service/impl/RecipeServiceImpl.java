package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.DailyPatientDao;
import com.gluxen.dao.RecipeDao;
import com.gluxen.service.RecipeService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/18.
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private DailyPatientDao dailyPatientDao;

    /**
     * 新增/更新病人药方
     * @param mainRecipe
     * @return
     */
    @Override
    @Transactional
    public JSONObject addMainRecipe(JSONObject mainRecipe){
        try{
            String inquiryId = mainRecipe.getString("inquiryId");
            try{
                if(recipeDao.isRecipe(Integer.parseInt(inquiryId)) == 0){
                    recipeDao.addMainRecipe(mainRecipe);
                    String mainRecipeId = mainRecipe.getString("mainRecipeId");
                    for(int i=0;i<5;i++){
                        JSONObject recipeObject = new JSONObject();
                        recipeObject.put("mainRecipeId",mainRecipeId);
                        recipeObject.put("amount", 0);
                        recipeObject.put("remarks","");
                        recipeDao.addRecipe(recipeObject);//此处recipeId值已经获得
                    }
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonUtil.errorJson(ErrorEnum.E_90007);
        }
    }

    /**
     * 新增/更改二级药方
     * @param recipe
     * @return
     */
    @Override
    public JSONObject addRecipe(JSONObject recipe){
        JSONArray recipeList = recipe.getJSONArray("recipeList");
        for(int i = 0;i < recipeList.size();i++){
            try{
                JSONObject jsonObject = recipeList.getJSONObject(i);
                recipeDao.addRecipe(jsonObject);
            }catch (RuntimeException e){
                e.printStackTrace();
                return CommonUtil.errorJson(ErrorEnum.E_90008);
            }
        }
        return CommonUtil.successJson();
    }

    /**
     * 增加药方细节
     * @param recipeDetail
     * @return
     */
    @Override
    public JSONObject addRecipeDetail(JSONObject recipeDetail){
//        Object recipeDetailList = recipeDetail.get("recieDetailList");
        JSONArray detailList = recipeDetail.getJSONArray("recieDetailList");
        String action = recipeDetail.getString("action");
        JSONArray newList = new JSONArray();

        try{
            if(action.equals("0")){
                for(int i=0; i<detailList.size(); i++){
                    JSONObject detail = detailList.getJSONObject(i);
                    detail.remove("detailId");
                    newList.add(0,detail);
                }
                recipeDao.addRecipeDetail(newList);
                return CommonUtil.successJson();
            }else{
                recipeDao.addRecipeDetail(detailList);
                return CommonUtil.successJson();
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90010);
        }
    }

    /**
     * 获取药方
     * @param inquiryId
     * @return
     */
    @Override
    public JSONObject getRecipe(int inquiryId){
        JSONObject returnData = new JSONObject();
        List<JSONObject> recipeList = recipeDao.getRecipe(inquiryId);
        returnData.put("recipleList",recipeList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 删除药方
     * @param mainRecipeId
     * @return
     */
    @Override
    public JSONObject deleteMainRecipe(int mainRecipeId){
        try{
            recipeDao.deleteMainRecipe(mainRecipeId);
            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90014);
        }
    }

    /**
     * 删除小药方
     * @param recipeId
     * @return
     */
    @Override
    public JSONObject deleteRecipe(int recipeId){
        try{
            recipeDao.deleteRecipe(recipeId);
            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90015);
        }
    }

    /**
     * 删除药方细节
     * @param detailId
     * @return
     */
    @Override
    public JSONObject deleteRecipeDetail(int detailId){
        try{
            recipeDao.deleteRecipeDetail(detailId);
            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90016);
        }
    }

    /**
     * 获取药方使用量
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject countRecipe(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = countMedicine(jsonObject);
        List<JSONObject> list = recipeDao.countRecipe(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 获取药物数量
     * @param jsonObject
     * @return
     */
    @Override
    public int countMedicine(JSONObject jsonObject){
        List<JSONObject> medicineList =  recipeDao.countMedicine(jsonObject);
        return medicineList.size();
    }

    /**
     * 获得最近一次问诊的药方
     * @param patientId
     * @return
     */
    @Override
    public JSONObject getLatestRecipe(Long patientId){
        JSONObject returnData = new JSONObject();
        int latestInquiryId =  dailyPatientDao.getLatestInquiry(patientId);
        List<JSONObject> recipeList = recipeDao.getRecipe(latestInquiryId);
        returnData.put("recipleList",recipeList);
        return CommonUtil.successJson(returnData);
    }

    /**
     * 更新二级药方
     * @param recipeObject
     * @return
     */
    @Override
    public JSONObject updateRecipe(JSONObject recipeObject){
        List<JSONObject> recipeListObject = new ArrayList<JSONObject>();
        JSONArray mainArray = recipeObject.getJSONArray("recipleList");
        for(int i=0;i<mainArray.size();i++){
            JSONObject mainRecipe = mainArray.getJSONObject(i);
            if(mainRecipe.size()!=0){
                mainRecipe.remove("recipeDetailList");
                recipeListObject.add(0,mainRecipe);
            }
//            JSONArray recipeArray = mainRecipe.getJSONArray("recipeList");
//            for(int j=0;j<recipeArray.size();j++){
//                JSONObject recipe = recipeArray.getJSONObject(j);
//                recipe.remove("recipeDetailList");
//                recileListObject.add(0,recipe);
//            }
        }
        recipeDao.updateRecipe(recipeListObject);
        return CommonUtil.successJson();
    }
}

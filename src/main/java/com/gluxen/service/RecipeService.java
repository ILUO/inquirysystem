package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Yang Xing Luo on 2018/1/18.
 */
public interface RecipeService {
    /**
     * 新增/更新病人主方
     * @param mainRecipeObject
     * @return
     */
    JSONObject addMainRecipe(JSONObject mainRecipeObject);


    /**
     * 新增病人二级药方
     * @param recipeObject
     * @return
     */
    JSONObject addRecipe(JSONObject recipeObject);

    /**
     * 新增二级药方细节
     * @param recipeDetail
     * @return
     */
    JSONObject addRecipeDetail(JSONObject recipeDetail);


    /**
     * 获取药方
     * @param inquiryId
     * @return
     */
    JSONObject getRecipe(int inquiryId);

    /**
     * 获取病人最近药方
     * @param patientId
     * @return
     */
    JSONObject getLatestRecipe(Long patientId);

    /**
     * 删除主方
     * @param mainRecipeId
     * @return
     */
    JSONObject deleteMainRecipe(int mainRecipeId);

    /**
     * 删除小药方
     * @param recipeId
     * @return
     */
    JSONObject deleteRecipe(int recipeId);


    /**
     * 删除药方细节
     * @param detailId
     * @return
     */
    JSONObject deleteRecipeDetail(int detailId);

    /**
     * 获取药物使用量
     * @param jsonObject
     * @return
     */
    JSONObject countRecipe(JSONObject jsonObject);

    /**
     * 获取药物种类
     * @param jsonObject
     * @return
     */
     int countMedicine(JSONObject jsonObject);

    /**
     * 更新二级药方
     * @param jsonObject
     * @return
     */
    JSONObject updateRecipe(JSONObject jsonObject);
}

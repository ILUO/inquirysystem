package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/18.
 */
@Repository
public interface RecipeDao {
    /**
     * 新增/更新药方
     * @param recipeObject
     */
    void addMainRecipe(JSONObject recipeObject);

    /**
     * 新增/更新二级药方
     * @param recipeDetailObject
     */
    void addRecipe(JSONObject recipeDetailObject);


    /**
     * 新增/更新药方细节
     * @param recipeDetailObjectList
     */
    void addRecipeDetail(Object recipeDetailObjectList);

    /**
     * 获取药方
     * @param inquiryId
     * @return
     */
    List<JSONObject> getRecipe(int inquiryId);

    /**
     * 删除主方
     * @param mainRecipeId
     */
    void deleteMainRecipe(int mainRecipeId);

    /**
     * 删除小药方
     * @param recipeId
     */
    void deleteRecipe(int recipeId);

    /**
     * 删除药方细节
     * @param detailId
     */
    void deleteRecipeDetail(int detailId);

    /**
     * 统计药物种类数量
     * @param jsonObject
     * @return
     */
    List<JSONObject> countMedicine(JSONObject jsonObject);

    /**
     * 统计药物使用量
     * @param jsonObject
     * @return
     */
    List<JSONObject> countRecipe(JSONObject jsonObject);


    /**
     * 更新二级药方
     * @param recipeObjectList
     */
    void updateRecipe(Object recipeObjectList);

    /**
     * 更新药方
     * @param mainRecipeId
     * @return
     */
    int isRecipe(int mainRecipeId);
}

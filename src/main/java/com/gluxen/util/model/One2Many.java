package com.gluxen.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * @author: hxy
 * @description: MyBatis的一对多JSON返回对象,
 * 使用时,可以在此新增一个成员变量和getter/setter,MyBatis就可以识别resultMap
 * @date: 2017/10/24 10:17
 */
public class One2Many extends JSONObject {

    private List<JSONObject> familyList;
    private List<JSONObject> choiceList;
    private List<JSONObject> basicHistoryList;
    private List<JSONObject> historyOptionList;
    private List<JSONObject> historyChoiceList;
    private List<JSONObject> recipeList;
    private List<JSONObject> recipeDetailList;
    private List<JSONObject> cityList;
    private List<JSONObject> amountList;
    private List<JSONObject> choiceQuestionList;
    private List<JSONObject> completionList;
    private List<JSONObject> historyList;
    private List<JSONObject> historyCompletionList;
    private List<JSONObject> downChoiceList;
    private List<JSONObject> downCompletionList;

    public List<JSONObject> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<JSONObject> historyList) {
        this.historyList = historyList;
    }

    public List<JSONObject> getCompletionList() {
        return completionList;
    }

    public void setCompletionList(List<JSONObject> completionList) {
        this.completionList = completionList;
    }

    public List<JSONObject> getChoiceQuestionList() {
        return choiceQuestionList;
    }

    public void setChoiceQuestionList(List<JSONObject> choiceQuestionList) {
        this.choiceQuestionList = choiceQuestionList;
    }

    public List<JSONObject> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<JSONObject> amountList) {
        this.amountList = amountList;
    }

    public List<JSONObject> getHistoryOptionList() {
        return historyOptionList;
    }

    public void setHistoryOptionList(List<JSONObject> historyOptionList) {
        this.historyOptionList = historyOptionList;
    }

    public List<JSONObject> getHistoryChoiceList() {
        return historyChoiceList;
    }

    public void setHistoryChoiceList(List<JSONObject> historyChoiceList) {
        this.historyChoiceList = historyChoiceList;
    }

    public List<JSONObject> getBasicHistoryList() {
        return basicHistoryList;
    }

    public void setBasicHistoryList(List<JSONObject> basicHistoryList) {
        this.basicHistoryList = basicHistoryList;
    }



    public List<JSONObject> getFamilyList() {
        return familyList;
    }

    public List<JSONObject> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<JSONObject> recipeList) {
        this.recipeList = recipeList;
    }

    public List<JSONObject> getRecipeDetailList() {
        return recipeDetailList;
    }

    public void setRecipeDetailList(List<JSONObject> recipeDetailList) {
        this.recipeDetailList = recipeDetailList;
    }

    public void setFamilyList(List<JSONObject> familyList) {
        this.familyList = familyList;
    }

    public List<JSONObject> getChoiceList() {
        return choiceList;
    }

    public List<JSONObject> getCityList() {
        return cityList;
    }

    public void setCityList(List<JSONObject> cityList) {
        this.cityList = cityList;
    }

    public void setChoiceList(List<JSONObject> choiceList) {
        this.choiceList = choiceList;
    }

    public List<JSONObject> getHistoryCompletionList() {
        return historyCompletionList;
    }

    public void setHistoryCompletionList(List<JSONObject> historyCompletionList) {
        this.historyCompletionList = historyCompletionList;
    }

    public List<JSONObject> getDownChoiceList() {
        return downChoiceList;
    }

    public void setDownChoiceList(List<JSONObject> downChoiceList) {
        this.downChoiceList = downChoiceList;
    }

    public List<JSONObject> getDownCompletionList() {
        return downCompletionList;
    }

    public void setDownCompletionList(List<JSONObject> downCompletionList) {
        this.downCompletionList = downCompletionList;
    }
}

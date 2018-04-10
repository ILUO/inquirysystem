package com.gluxen.util;

import com.alibaba.fastjson.JSONObject;


/**
 * Created by Yang Xing Luo on 2018/1/30.
 */
public class RowName {

    public RowName(){

    }

    public JSONObject getRowName(){
        JSONObject rowName = new JSONObject();
        rowName.put("rowNum","rowNum");
        rowName.put("patientId","病人ID");
        rowName.put("name","姓名");
        rowName.put("inquiryType","问诊类型");
        rowName.put("inquiryId","问诊ID");
        rowName.put("inquiryDate","问诊日期");
        rowName.put("birthday","生日");
        rowName.put("age","年龄");
        rowName.put("gender","性别");
        rowName.put("nationality","国籍");
        rowName.put("national","民族");
        rowName.put("city","来自城市");
        rowName.put("height","身高");
        rowName.put("weight","体重");
        rowName.put("occupation","职业");
        rowName.put("bloodTrans","输血史");
        rowName.put("smoke","吸烟史");
        rowName.put("drink","饮酒史");
        rowName.put("dailyDrink","每日饮酒(两)");
        rowName.put("dailySmoke","每日吸烟(支)");
        rowName.put("contraception","避孕");
        rowName.put("contactHistory","接触史");
        rowName.put("contactHistoryRemark","接触史备注");
        rowName.put("eatingHabits","饮食习惯");
        rowName.put("eatingHabitsRemark","饮食习惯备注");
        rowName.put("pastHistory","既往病史");
        rowName.put("belief","宗教信仰");
        rowName.put("infectionHistory","传染病史");
        rowName.put("infectiousHistoryRemark","传染病史备注");
        rowName.put("traumaHistory","外伤史");
        rowName.put("familyHistory","家族病史");
        rowName.put("operationHistory","手术史");
        rowName.put("drugAllergy","药物过敏");
        rowName.put("drugAllergyRemark","药物过敏备注");
        rowName.put("allergy","过敏史");
        rowName.put("allergyRemark","过敏物备注");
        rowName.put("heredityHistory","遗传病史");
        rowName.put("heredityHistoryRemark","遗传病史备注");
        rowName.put("quitDrinkTime","戒酒时长");
        rowName.put("quitSmokeTime","戒烟时长");
        rowName.put("menarche","初潮");
        rowName.put("menopauseAge","绝经年龄");
        rowName.put("menstrualCycle","月经周期");
        rowName.put("menstruationPeriod","持续时间");
        rowName.put("pregnant","怀孕");
        rowName.put("birth","生育");
        rowName.put("prematureLabour","早产");
        rowName.put("abortion","流产");
        rowName.put("marriage","婚姻史");
        rowName.put("quitSmoke","戒烟");
        rowName.put("quitDrink","戒酒");
        rowName.put("pregnant","怀孕");
        rowName.put("prematureLabour","早产");
        rowName.put("abortion","流产");
        rowName.put("birth","生育");
        rowName.put("诊断与主诉","诊断与主诉");
        rowName.put("主要症状","主要症状");
        rowName.put("饭量","饭量");
        rowName.put("食欲","食欲");
        rowName.put("饥饿感","饥饿感");
        rowName.put("腹部饱涨","腹部饱涨");
        rowName.put("腹痛情况","腹痛情况");
        rowName.put("纳后打嗝","纳后打嗝");
        rowName.put("消化","消化");
        rowName.put("饮食喜畏","饮食喜畏");
        rowName.put("入睡快慢","入睡快慢");
        rowName.put("睡眠时长","睡眠时长");
        rowName.put("睡眠质量","睡眠质量");
        rowName.put("起夜次数(最小值)","起夜次数(最小值)");
        rowName.put("起夜次数(最大值)","起夜次数(最大值)");
        rowName.put("做梦次数","做梦次数");
        rowName.put("噩梦","噩梦");
        rowName.put("午睡否","午睡否");
        rowName.put("大便频率(天)","大便频率(天)");
        rowName.put("大便频率(次)","大便频率(次)");
        rowName.put("大便外观","大便外观");
        rowName.put("大便顺畅","大便顺畅");
        rowName.put("颜色","颜色");
        rowName.put("大便气味","大便气味");
        rowName.put("粘连否","粘连否");
        rowName.put("便血否","便血否");
        rowName.put("开塞露否","开塞露否");
        rowName.put("造瘘否","造瘘否");
        rowName.put("小便颜色","小便颜色");
        rowName.put("尿量","尿量");
        rowName.put("力量强度","力量强度");
        rowName.put("尿不尽","尿不尽");
        rowName.put("尿等待","尿等待");
        rowName.put("尿频","尿频");
        rowName.put("利尿剂","利尿剂");
        rowName.put("末次开始时间","末次开始时间");
        rowName.put("末次结束时间","末次结束时间");
        rowName.put("痛经","痛经");
        rowName.put("周期规律","周期规律");
        rowName.put("腰部不适","腰部不适");
        rowName.put("头部不适","头部不适");
        rowName.put("乳房不适","乳房不适");
        rowName.put("倒经","倒经");
        rowName.put("绝经原因","绝经原因");
        return rowName;
    }

    public String[] getRowList(){
        String[] rowList = new String[]{
                "rowNum","patientId", "name","inquiryType","inquiryId","inquiryDate","birthday",
                "age","gender","nationality","national","city","height","weight","occupation",
                "bloodTrans","smoke","drink","quitSmoke","quitDrink","dailyDrink","dailySmoke","contraception",
                "contactHistory","contactHistoryRemark","eatingHabits","eatingHabitsRemark","pastHistory",
                "belief","infectionHistory","infectiousHistoryRemark","traumaHistory",
                "familyHistory","operationHistory","drugAllergy","drugAllergyRemark",
                "allergy","allergyRemark","heredityHistory","heredityHistoryRemark",
                "quitDrinkTime","quitSmokeTime","menarche","menopauseAge",
                "menstrualCycle","menstruationPeriod","pregnant","birth","prematureLabour",
                "abortion","marriage",
                "诊断与主诉","主要症状",
                "饭量","食欲","饥饿感","腹部饱涨","腹痛情况","纳后打嗝","消化","饮食喜畏",
                "入睡快慢","睡眠时长","睡眠质量","起夜次数(最小值)","起夜次数(最大值)",
                "做梦次数","噩梦","午睡否","大便频率(天)","大便频率(次)","大便外观",
                "大便顺畅","颜色","大便气味","粘连否","便血否","开塞露否","造瘘否",
                "小便颜色","尿量","力量强度","尿不尽","尿等待","尿频","利尿剂","末次开始时间",
                "末次结束时间","痛经","周期规律","腰部不适","头部不适","乳房不适","倒经","绝经原因","绝经原因"
        };

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rowNum","rowNum");

        return rowList;
    }

    public String[] chineseRowList(){
        String[] rowList = new String[]{
                "rowNum","病人Id", "姓名","问诊类型","问诊Id","问诊日期","生日",
                "年龄","性别","国籍","民族","来自城市","身高(cm)","体重(kg)","职业",
                "输血史","吸烟史","饮酒史","戒烟","戒酒","每日饮酒(两)","每日吸烟(支)","避孕",
                "接触史","接触史备注","饮食习惯","饮食习惯备注","既往病史",
                "宗教信仰","传染病史","传染病史备注","外伤史",
                "家族病史","手术史","药物过敏","药物过敏备注",
                "过敏物","过敏物备注","遗传病史","遗传病史备注",
                "戒酒时长","戒烟时长","初潮","绝经年龄",
                "月经周期","持续时间","怀孕","生育","早产",
                "流产","婚姻史",
                "诊断与主诉","主要症状",
                "饭量","食欲","饥饿感","腹部饱涨","腹痛情况","纳后打嗝","消化","饮食喜畏",
                "入睡快慢","睡眠时长","睡眠质量","起夜次数(最小值)","起夜次数(最大值)",
                "做梦次数","噩梦","午睡否","大便频率(天)","大便频率(次)","大便外观",
                "大便顺畅","颜色","大便气味","粘连否","便血否","开塞露否","造瘘否",
                "小便颜色","尿量","力量强度","尿不尽","尿等待","尿频","利尿剂","末次开始时间",
                "末次结束时间","痛经","周期规律","腰部不适","头部不适","乳房不适","倒经","绝经原因","绝经原因"
        };
        return rowList;
    }

}

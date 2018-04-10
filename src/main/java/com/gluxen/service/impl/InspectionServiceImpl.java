package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.InspectionIndexDao;
import com.gluxen.service.InspectionService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/19.
 */
@Service
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    private InspectionIndexDao inspectionIndexDao;
    /**
     * 添加检查指标
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject addInspectionIndex(JSONObject jsonObject){
        System.out.println(jsonObject.toString());
        try {
            inspectionIndexDao.addInspectionIndex(jsonObject);
            return CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90008);
        }
    }

    /**
     * 获取检查指标
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getInspectionIndex(JSONObject jsonObject){
        JSONObject returnData = new JSONObject();
//        CommonUtil.fillPageParam(jsonObject);
//        int count = inspectionIndexDao.countInspection(jsonObject);
        List<JSONObject> list = inspectionIndexDao.getInspectionIndex(jsonObject);
        returnData.put("indexList",list);
        return CommonUtil.successJson(returnData);
//        return  CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 删除病人检查指标
     * @param indexId
     * @return
     */
    @Override
    public JSONObject deleteInspection(int indexId){
        try{
            inspectionIndexDao.deleteInspection(indexId);
            return  CommonUtil.successJson();
        }catch (RuntimeException e){
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_90012);
        }
    }
}

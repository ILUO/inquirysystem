package com.gluxen.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/27.
 */
@Repository
public interface PictureDao {
    /**
     * 上传图片
     * @param jsonObject
     */
    void uploadPicture(JSONObject jsonObject);


    /**
     * 获取图片url List
     * @param jsonObject
     * @return
     */
    List<JSONObject> getPictureList(JSONObject jsonObject);


    /**
     * 计算图片张数
     * @param jsonObject
     * @return
     */
    int countPicture(JSONObject jsonObject);

    /**
     * 删除图片
     * @param pictureId
     */
    void deletePicture(int pictureId);

    /**
     * 获取图片位置
     * @param pictureId
     * @return
     */
    JSONObject getPicture(Integer pictureId);

}

package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


/**
 * Created by Yang Xing Luo on 2018/1/26.
 */
public interface PictureService {
    /**
     * 上传图片
     * @param fileList
     * @param inquiryId
     * @param patientName
     * @return
     */
    JSONObject saveImg(List<MultipartFile> fileList, Integer inquiryId, String patientName, Long patientId);

    /**
     * 获取图片列表
     * @param jsonObject
     * @return
     */
    JSONObject getPictureList(JSONObject jsonObject);

    /**
     * 删除图片
     * @param pictureId
     * @return
     */
    JSONObject deletePicture(Integer pictureId);
}

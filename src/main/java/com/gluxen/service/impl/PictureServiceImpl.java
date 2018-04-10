package com.gluxen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.dao.PictureDao;
import com.gluxen.service.PictureService;
import com.gluxen.util.CommonUtil;
import com.gluxen.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yang Xing Luo on 2018/1/26.
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    /**
     * 上传图片
     * @param fileList
     * @param inquiryId
     * @param patientName
     * @param patientId
     * @return
     */
    @Override
    public JSONObject saveImg(List<MultipartFile> fileList, Integer inquiryId, String patientName, Long patientId) {
        JSONObject pictureInfo = new JSONObject();
        for(int i = 0 ; i < fileList.size() ; i++) {
            LocalDateTime time = LocalDateTime.now();
            int second = time.getNano();
            MultipartFile file = fileList.get(i);
            if (!file.isEmpty()) {
                try {
                    Properties prop = System.getProperties();
                    String path = "";

                    String os = prop.getProperty("os.name");
                    if (os != null && os.toLowerCase().indexOf("linux") > -1) {
                        path = "/usr/local/inquirysystem/picture/"+ patientName +"_"+ String.valueOf(patientId)+ "_" +String.valueOf(inquiryId)+"_"+String.valueOf(second) +".jpg";
                    }else{
                        path = "D:\\inquirysystem\\picture\\" + patientName +"_"+ String.valueOf(patientId)+ "_" +String.valueOf(inquiryId)+"_"+String.valueOf(second) +".jpg";
                    }
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(path)));//保存图片到目录下
//                    BufferedOutputStream out = new BufferedOutputStream(
//                            new FileOutputStream(new File("/usr/picture/"+ patientName +"_"+ String.valueOf(patientId)+ "_" +String.valueOf(second)+"_"+String.valueOf(i) +".jpg")));//保存图片到目录下
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
//                    String pictureUrl = "/usr/picture/"+ patientName + "_"+ String.valueOf(patientId)+ "_"  + String.valueOf(inquiryId)+"_"+String.valueOf(second) +".jpg";
                    String pictureUrl = patientName +"_"+ String.valueOf(patientId)+ "_" +String.valueOf(inquiryId)+"_"+String.valueOf(second) +".jpg";
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String inquiryDate = format.format(date);
                    pictureInfo.put("inquiryDate",inquiryDate);
                    pictureInfo.put("pictureUrl",pictureUrl);
                    pictureInfo.put("inquiryId",inquiryId);
                    pictureInfo.put("patientId",patientId);
                    pictureDao.uploadPicture(pictureInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    return CommonUtil.errorJson(ErrorEnum.E_90017);
                }
            } else {
                return CommonUtil.errorJson(ErrorEnum.E_90017);
            }
        }
        return CommonUtil.successJson();
    }

    /**
     * 获取图片列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getPictureList(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = pictureDao.countPicture(jsonObject);
        List<JSONObject> list = pictureDao.getPictureList(jsonObject);
        return  CommonUtil.successPage(jsonObject,list,count);
    }

    /**
     * 删除图片
     * @param pictureId
     * @return
     */
    @Override
    public JSONObject deletePicture(Integer pictureId){
        String pictureUrl = pictureDao.getPicture(pictureId).getString("pictureUrl");
        File file = new File(pictureUrl);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        try{
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    pictureDao.deletePicture(pictureId);
                    return CommonUtil.successJson();
                } else {
                    return  CommonUtil.errorJson(ErrorEnum.E_90018);
                }
            } else {
                return CommonUtil.errorJson(ErrorEnum.E_90018);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  CommonUtil.errorJson(ErrorEnum.E_90018);
        }
    }
}

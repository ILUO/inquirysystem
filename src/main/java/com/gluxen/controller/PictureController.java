package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.PictureService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/26.
 */
@RestController
@RequestMapping("/api/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    /**
     * 上传图片
     * @param fileList
     * @return
     */
    @PostMapping(value="/upload")
    public JSONObject uploadPicture(
            @RequestPart(value = "picture")  List<MultipartFile> fileList,
            @RequestParam(value = "inquiryId") Integer inquiryId,
            @RequestParam(value = "patientName") String patientName,
            @RequestParam(value = "patientId") Long patientId
            ) {
        return pictureService.saveImg(fileList,inquiryId,patientName,patientId);
    }

    /**
     * 获取某个病人的图片列表
     * @param pageNum
     * @param pageRow
     * @param inquiryId
     * @return
     */
    @GetMapping(value = "/getPictureList")
    public JSONObject getPictureList(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "问诊Id") @RequestParam(value="inquiryId",required=false) Long inquiryId
    ){
        JSONObject request = new JSONObject();
        request.put("pageRow",pageRow);
        request.put("pageNum",pageNum);
        request.put("inquiryId",inquiryId);
        return pictureService.getPictureList(request);
    }

    /**
     * 删除图片
     * @param pictureId
     * @return
     */
    @DeleteMapping(value = "/deletePicture")
    public JSONObject deletePicture(
            @ApiParam(value = "图片Id", required = true) @RequestParam("pictureId") int pictureId
    ){
        return pictureService.deletePicture(pictureId);
    }
}

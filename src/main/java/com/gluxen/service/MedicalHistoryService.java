package com.gluxen.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Yang Xing Luo on 2018/1/29.
 */

public interface MedicalHistoryService {

    /**
     * 病历导出为Excel
     */
    void downloadExcel(HttpServletRequest request,HttpServletResponse response);
}

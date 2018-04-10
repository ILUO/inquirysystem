package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.LoginService;
import com.gluxen.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yang Xing Luo on 2018/2/2.
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password");
        return loginService.authLogin(requestJson);
    }


    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }
}

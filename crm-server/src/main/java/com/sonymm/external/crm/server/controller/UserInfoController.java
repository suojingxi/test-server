package com.sonymm.external.crm.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonymm.external.crm.server.service.UserInfoService;

@Controller
@RequestMapping(value="/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/getUserInfoList.shtml")
    public String getUserInfoList(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) {
 
        List<Map<String, Object>> userInfos = userInfoService.selectUserInfoList();
        request.setAttribute("userInfos", userInfos);
        return "user/getUserInfoList";
    }
 
    @RequestMapping(value = "/goToEditUserInfo.shtml")
    public String goToEditUserInfo(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap, String id) {
 
        if (StringUtils.isNotBlank(id)) {
        	Map<String, Object> userInfo = userInfoService.getUserInfoById(id);
            request.setAttribute("userInfo", userInfo);
        }
        return "user/editUserInfo";
    }
 
    @RequestMapping(value = "/editUserInfo.shtml")
    @ResponseBody
    public String editUserInfo(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) {
 
        //将form表单参数封装为map
    	Map<String, Object> parameters = new HashMap<String, Object>();
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            parameters.put("id", id);
        }
        String name = request.getParameter("name");
        if (StringUtils.isNotBlank(name)) {
            parameters.put("name", name);
        }
        String password = request.getParameter("password");
        if (StringUtils.isNotBlank(password)) {
            parameters.put("password", password);
        }
        String age = request.getParameter("age");
        if (StringUtils.isNotBlank(age)) {
            parameters.put("age", age);
        }
        return userInfoService.editUserInfo(parameters);
    }
 
 
    @RequestMapping(value = "/deleteUserInfo.shtml")
    @ResponseBody
    public String deleteUserInfo(HttpServletRequest request,
            HttpServletResponse response, ModelMap modelMap) {
 
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            return userInfoService.deleteUserInfo(id);
        }
        return "error";
    }
}

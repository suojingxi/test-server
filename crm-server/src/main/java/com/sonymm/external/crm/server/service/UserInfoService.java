package com.sonymm.external.crm.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.sonymm.external.crm.server.service.common.HttpClientService;
import com.sonymm.external.crm.server.util.JsonUtil;

@Service
public class UserInfoService extends HttpClientService {

	public List<Map<String, Object>> selectUserInfoList(){
        httpclient = HttpClients.createDefault(); 
        List<Map<String, Object>> jsonObjects = null;
        String json = getHttpClient(IP + "user/getUserInfoList");
        if (StringUtils.isNotBlank(json)) {
            if (json.equals("error")) {
                return null;
            } else {
                jsonObjects = JsonUtil.getObjectList(json);
            }
        }
        return jsonObjects;
    }
 
    public Map<String, Object> getUserInfoById(String id) {
        httpclient = HttpClients.createDefault(); 
        Map<String, Object> jsonObject = null;
        //将id属性和值存到http请求参数队列 
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", id)); 
        String json = postHttpClient(IP + "user/getUserInfoById", formparams);
        if (StringUtils.isNotBlank(json)) {
            if (json.equals("error")) {
                return null;
            } else {
            	Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", Integer.class);
                jsonObject = (Map<String, Object>)JsonUtil.getObject(json, map);
            }
        }
        return jsonObject;
    }
 
    public String editUserInfo(Map<String, Object> parameters) {
        httpclient = HttpClients.createDefault(); 
        //将form表单数据存到http请求参数队列 
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Iterator iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            formparams.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString())); 
        }
        return postHttpClient(IP + "user/editUserInfo", formparams);
    }
 
    public String deleteUserInfo(String id) {
        httpclient = HttpClients.createDefault(); 
        //将id属性和值存到http请求参数队列 
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("id", id)); 
        return postHttpClient(IP + "user/deleteUserInfoById", formparams);
    }
}

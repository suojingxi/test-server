package com.sonymm.external.crm.server.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
     * 根据json和属性值返回对应Map类型实体，key：属性名；value：属性值
     * @param json
     * @param map 实体类对应属性值
     * @return
     */
    public static Map<String, Object> getObject(String json, Map map) {
    	Map<String, Object> jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
 
    /**
     * 根据json返回List<map>类型实体集合，key：属性名；value：属性值
     * @param json
     * @param map
     * @return
     */
    public static List<Map<String, Object>> getObjectList(String json) {
        JSONArray array = JSONArray.fromObject(json);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Iterator iter = array.iterator(); iter.hasNext();) {
        	Map<String, Object> jsonObject = (Map<String, Object>) iter.next();
            list.add(jsonObject);
        }
        return list;
    }
}

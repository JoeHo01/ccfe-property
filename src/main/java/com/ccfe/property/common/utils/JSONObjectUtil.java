package com.ccfe.property.common.utils;

import net.sf.json.JSONObject;

public class JSONObjectUtil {

    public static Object get(JSONObject jsonObject, String key) {
        String[] keys = key.split("\\.");
        for (String k : keys) {
            Object o = jsonObject.get(k);
            if (o instanceof JSONObject) {
                jsonObject = (JSONObject) o;
            }
        }
        return jsonObject.get(keys[keys.length - 1]);
    }
}

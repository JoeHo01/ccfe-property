package com.ccfe.property.http.mvc.service;

import com.ccfe.property.common.utils.JSONObjectUtil;
import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.DO.LogDO;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class PropertyLogger {

    private final MongoSupport mongoSupport;

    private final HttpServletRequest request;

    private final String COLLECTION_CLASS = "collection_class";

    private final String ID = "id";

    private final String VALUE = "value";

    private final String REGION = "region";

    private final String BRAND = "brand";

    private final String NAME = "name";

    @Autowired
    public PropertyLogger(MongoSupport mongoSupport, HttpServletRequest request) {
        this.mongoSupport = mongoSupport;
        this.request = request;
    }

    public <T> void log(Class<T> clazz, String operation, String id, String field, String value){
        List<Map<String, Object>> original = new ArrayList<>();
        List<Map<String, String>> documents = new ArrayList<>();

        for (String i : id.split(",")){
            JSONObject target = JSONObject.fromObject(mongoSupport.findById(clazz, i));

            Map<String, Object> originalInfo = new HashMap<>();
            originalInfo.put(COLLECTION_CLASS, clazz.getName());
            originalInfo.put(ID, i);
            originalInfo.put(VALUE, JSONObjectUtil.get(target, field));
            original.add(originalInfo);

            Map<String, String> document = new HashMap<>();
            document.put(REGION, (String) target.get(REGION));
            document.put(BRAND, target.get(BRAND).toString());
            document.put(NAME, target.get(NAME).toString());
            documents.add(document);
        }
        mongoSupport.addDocument(new LogDO(request.getHeader("token"), new Date().getTime(), operation, field, value, documents, original));
    }
}

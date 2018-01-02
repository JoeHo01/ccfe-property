package com.ccfe.property.http.mvc.service;

import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.DO.PropertiesBundleDO;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertiesService {
    private final MongoSupport mongoSupport;

    private final String PROPERTIES = "properties.";
    private final String PERMISSION = "permission";

    @Autowired
    public PropertiesService(MongoSupport mongoSupport) {
        this.mongoSupport = mongoSupport;
    }

    public UpdateResults update(String ids, String propertyName, String column, String value) {
        String propertyColumn = PROPERTIES + propertyName + Mapper.IGNORED_FIELDNAME + column;
        return mongoSupport.update(PropertiesBundleDO.class, propertyColumn, value, ids.split(","));
    }

    public UpdateResults addPermissions(String ids, String permissions) {
        List<String> permissionsList = Arrays.asList(permissions.split(","));
        return mongoSupport.addToArray(PropertiesBundleDO.class, PERMISSION, permissionsList, ids.split(","));
    }

    public UpdateResults removePermissions(String ids, String permissions) {
        List<String> permissionsList = Arrays.asList(permissions.split(","));
        return mongoSupport.removeFromArray(PropertiesBundleDO.class, PERMISSION, permissionsList, ids.split(","));
    }

    public <T> List<T> query(Class<T> clazz, Map<String, String> included, Map<String,String> excepted) {
        Map<String, List<Object>> in = new HashMap<>();
        Set<String> inKeys = included.keySet();
        for (String column : inKeys){
            in.put(column, Arrays.asList(included.get(column).split(",")));
        }

        Map<String, List<Object>> nin = new HashMap<>();
        Set<String> ninKeys = excepted.keySet();
        for (String column : ninKeys){
            nin.put(column, Arrays.asList(excepted.get(column).split(",")));
        }
        return mongoSupport.query(clazz, in, nin);
    }
}

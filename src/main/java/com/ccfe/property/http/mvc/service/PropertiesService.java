package com.ccfe.property.http.mvc.service;

import com.ccfe.property.common.utils.JSONObjectUtil;
import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.DO.PropertiesBundleDO;
import com.ccfe.property.http.mvc.entity.DO.PropertyDO;
import com.ccfe.property.http.mvc.entity.DO.RoleDO;
import net.sf.json.JSONObject;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertiesService {

    private final MongoSupport mongoSupport;

    private final PropertyLogger propertyLogger;

    private final String DELIMITER = ",";

    @Autowired
    public PropertiesService(MongoSupport mongoSupport, PropertyLogger propertyLogger) {
        this.mongoSupport = mongoSupport;
        this.propertyLogger = propertyLogger;
    }

    public UpdateResults setValue(String ids, String field, String value) {
        propertyLogger.log(PropertiesBundleDO.class, "Set Value in Property", ids, field, value);
        return mongoSupport.update(PropertiesBundleDO.class, field, value, ids.split(DELIMITER));
    }

    public UpdateResults addValue(String ids, String field, String value) {
        propertyLogger.log(PropertiesBundleDO.class, "Add Value in Property", ids, field, value);
        for (String id : ids.split(DELIMITER)) {
            String original = JSONObjectUtil.get(JSONObject.fromObject(mongoSupport.findById(PropertiesBundleDO.class, id)), field).toString();
            if(!"".equals(original)){
                value = original + DELIMITER + value;
            }
            mongoSupport.update(PropertiesBundleDO.class, field, value, id);
        }
        return null;
    }

    public UpdateResults removeValue(String ids, String field, String value) {
        propertyLogger.log(PropertiesBundleDO.class, "Remove Value in Property", ids, field, value);
        for (String id : ids.split(DELIMITER)) {
            String original = JSONObjectUtil.get(JSONObject.fromObject(mongoSupport.findById(PropertiesBundleDO.class, id)), field).toString();
            String last = original.replaceAll(value, "").replaceAll(",,",",");
            if (last.startsWith(",")) last = last.substring(1);
            if (last.endsWith(",")) last = last.substring(0, last.length() - 1);
            mongoSupport.update(PropertiesBundleDO.class, field, last, id);
        }
        return null;
    }

    public UpdateResults addPermissions(String ids, String field, String permissions) {
        propertyLogger.log(RoleDO.class, "Add Permissions", ids, field, permissions);
        return mongoSupport.addToArray(RoleDO.class, field, Arrays.asList(permissions.split(DELIMITER)), ids.split(DELIMITER));
    }

    public UpdateResults removePermissions(String ids, String field, String permissions) {
        propertyLogger.log(RoleDO.class, "Remove Permissions", ids, field, permissions);
        return mongoSupport.removeFromArray(RoleDO.class, field, Arrays.asList(permissions.split(DELIMITER)), ids.split(DELIMITER));
    }

    public <T> List<T> query(Class<T> clazz, Map<String, String> included, Map<String, String> excepted) {
        Map<String, List<Object>> in = new HashMap<>();
        Set<String> inKeys = included.keySet();
        for (String column : inKeys) {
            in.put(column, Arrays.asList(included.get(column).split(DELIMITER)));
        }

        Map<String, List<Object>> nin = new HashMap<>();
        Set<String> ninKeys = excepted.keySet();
        for (String column : ninKeys) {
            nin.put(column, Arrays.asList(excepted.get(column).split(DELIMITER)));
        }
        return mongoSupport.query(clazz, in, nin);
    }

    public Map<String, Map<String, String>> queryByProperties(Map<String, String> included, Map<String, String> excepted) {
        Map<String, List<Object>> in = new HashMap<>();
        Set<String> inKeys = included.keySet();
        for (String column : inKeys) {
            in.put(column, Arrays.asList(included.get(column).split(DELIMITER)));
        }

        Map<String, List<Object>> nin = new HashMap<>();
        Set<String> ninKeys = excepted.keySet();
        for (String column : ninKeys) {
            nin.put(column, Arrays.asList(excepted.get(column).split(DELIMITER)));
        }

        Map<String, Map<String, String>> result = new HashMap<>();

        for (PropertiesBundleDO propertiesBundleDO : mongoSupport.query(PropertiesBundleDO.class, in, nin)) {
            Map<String, PropertyDO> properties = propertiesBundleDO.getProperties();
            for (String key : properties.keySet()){
                if (result.get(key) == null){
                    Map<String, String> value = new HashMap<>();
                    value.put(propertiesBundleDO.getName(), properties.get(key).getValue());
                    result.put(key, value);
                }else {
                    Map<String, String> value = result.get(key);
                    value.put(propertiesBundleDO.getName(), properties.get(key).getValue());
                    result.put(key, value);
                }
            }
        }

        return result;
    }

//    public List<LogDO> queryLog(long begin, long end, String operation, String field, Page page) {
//
//
//    }
}

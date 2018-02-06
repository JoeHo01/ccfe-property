package com.ccfe.property.http.mvc.service;

import com.ccfe.property.common.utils.FileUtil;
import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.DO.PropertiesBundleDO;
import com.ccfe.property.http.mvc.entity.DO.PropertyDO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertiesValidation {

    private final MongoSupport mongoSupport;

    private Map<String, JSONObject> models = new HashMap<>();

    @Autowired
    public PropertiesValidation(MongoSupport mongoSupport) {
        this.mongoSupport = mongoSupport;
    }

    public void initModel() {
        String path = System.getProperty("user.dir") + "/src/main/resources/properties_format.json";
        String jsonWords = FileUtil.readFile(path);
        JSONArray modelArray = JSONArray.fromObject(jsonWords);

        for (int i = 0; i < modelArray.size(); i++) {
            JSONObject jsonObject = modelArray.getJSONObject(i);
            this.models.put(jsonObject.getString("scope"), jsonObject);
        }
    }

    public void validateProperties(List<PropertiesBundleDO> propertiesBundles) {
        for (PropertiesBundleDO propertiesBundle : propertiesBundles) {
            validate(this.models.get(propertiesBundle.getScope()), propertiesBundle);
        }
    }

    private void validate(JSONObject model, PropertiesBundleDO propertiesBundle) {
        Set<String> newProperties = new HashSet<>();

        JSONObject modelProperties = model.getJSONObject("properties");
        Map<String, PropertyDO> validatedProperties = propertiesBundle.getProperties();

        newProperties.addAll(modelProperties.keySet());
        newProperties.removeAll(validatedProperties.keySet());

        System.out.println(propertiesBundle.getScope() + " : "+ newProperties);

        Set<String> newProperties2 = new HashSet<>();

        newProperties2.addAll(validatedProperties.keySet());
        newProperties2.removeAll(modelProperties.keySet());

        System.out.println(propertiesBundle.getName() + " : "+newProperties2);

        System.out.println();

//        for (String key : newProperties) {
//            addProperty(modelProperties.getJSONObject(key), propertiesBundle.getId());
//        }
    }

    private void addProperty(JSONObject property, String id) {
        String propertyName = "properties." + property.getString("key");
        mongoSupport.update(PropertiesBundleDO.class, propertyName, property, id);
    }
}

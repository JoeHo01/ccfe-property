package com.ccfe.property.http.mvc.service;

import com.ccfe.property.common.utils.FileUtil;
import com.ccfe.property.http.mvc.entity.PropertiesBundleDO;
import com.ccfe.property.http.mvc.entity.PropertyDO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertiesValidation {

    private final Datastore datastore;

    private Map<String, JSONObject> models = new HashMap<>();

    @Autowired
    public PropertiesValidation(Datastore datastore) {
        this.datastore = datastore;
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

        for (String key : newProperties) {
            addProperty(modelProperties.getJSONObject(key), propertiesBundle.getId());
        }
    }

    private void addProperty(JSONObject property, ObjectId id) {
        String propertyName = "properties." + property.getString("key");
        Query<PropertiesBundleDO> query = datastore.createQuery(PropertiesBundleDO.class).field(Mapper.ID_KEY).equal(id);


        //UpdateOps 在创建时会将全部字段逆序排列
        UpdateOperations<PropertiesBundleDO> update = datastore.createUpdateOperations(PropertiesBundleDO.class);

        update.set(propertyName,property);
        datastore.update(query, update);
    }
}

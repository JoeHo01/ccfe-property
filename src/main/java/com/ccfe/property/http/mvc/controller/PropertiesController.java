package com.ccfe.property.http.mvc.controller;

import com.ccfe.property.common.response.DataResponse;
import com.ccfe.property.common.response.DataWrapper;
import com.ccfe.property.http.mvc.entity.DO.PropertiesBundleDO;
import com.ccfe.property.http.mvc.service.PropertiesService;
import net.sf.json.JSONObject;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("property")
public class PropertiesController {

    private final PropertiesService propertiesService;

    private final String entityPackage = "com.ccfe.property.http.mvc.entity";

    private final String entitySuffix = "DO";

    @Autowired
    public PropertiesController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> update(
            @RequestParam("ids") String ids,
            @RequestParam("updatedProperty") String updatedProperty,
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        return DataResponse.succeed(propertiesService.update(ids, updatedProperty, column, value));
    }

    @RequestMapping(value = "addPermissions", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> addPermissions(
            @RequestParam("ids") String ids,
            @RequestParam("permissions") String permissions
    ) {
        return DataResponse.succeed(propertiesService.addPermissions(ids, permissions));
    }

    @RequestMapping(value = "removePermissions", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> removePermissions(
            @RequestParam("ids") String ids,
            @RequestParam("permissions") String permissions
    ) {
        return DataResponse.succeed(propertiesService.removePermissions(ids, permissions));
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public <T> DataWrapper<List<T>> query(
            @RequestParam("dbName") String dbName,
            @RequestParam("included") String included,
            @RequestParam(value = "excepted", required = false) String excepted
    ) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(entityPackage + "." + entitySuffix + "." + dbName + entitySuffix);
            return DataResponse.succeed(propertiesService.query(clazz, (Map<String, String>) JSONObject.fromObject(included), (Map<String, String>) JSONObject.fromObject(excepted)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return DataResponse.succeed(null);
        }
    }
}

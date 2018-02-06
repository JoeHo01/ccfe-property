package com.ccfe.property.http.mvc.controller;

import com.ccfe.property.common.response.*;
import com.ccfe.property.http.mvc.entity.DO.LogDO;
import com.ccfe.property.http.mvc.entity.DO.PropertiesBundleDO;
import com.ccfe.property.http.mvc.entity.DO.RoleDO;
import com.ccfe.property.http.mvc.service.PropertiesService;
import net.sf.json.JSONObject;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("property")
public class PropertiesController {

    private final PropertiesService propertiesService;

    private final String DOT = ".";

    private final String PROPERTIES = "properties";

    private final String PERMISSIONS = "permissions";

    @Autowired
    public PropertiesController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @RequestMapping(value = "setValue", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> setValue(
            @RequestParam("ids") String ids,
            @RequestParam("property") String property,
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        return DataResponse.succeed(propertiesService.setValue(ids, PROPERTIES + DOT + property + DOT + column, value));
    }

    @RequestMapping(value = "addValue", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> addValue(
            @RequestParam("ids") String ids,
            @RequestParam("property") String property,
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        return DataResponse.succeed(propertiesService.addValue(ids, PROPERTIES + DOT + property + DOT + column, value));
    }

    @RequestMapping(value = "deleteValue", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> deleteValue(
            @RequestParam("ids") String ids,
            @RequestParam("property") String property,
            @RequestParam("column") String column,
            @RequestParam("value") String value
    ) {
        return DataResponse.succeed(propertiesService.removeValue(ids, PROPERTIES + DOT + property + DOT + column, value));
    }

    @RequestMapping(value = "addPermissions", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> addPermissions(
            @RequestParam("ids") String ids,
            @RequestParam("permissions") String permissions
    ) {
        return DataResponse.succeed(propertiesService.addPermissions(ids, PERMISSIONS, permissions));
    }

    @RequestMapping(value = "deletePermissions", method = RequestMethod.POST)
    public DataWrapper<UpdateResults> deletePermissions(
            @RequestParam("ids") String ids,
            @RequestParam("permissions") String permissions
    ) {
        return DataResponse.succeed(propertiesService.removePermissions(ids, PERMISSIONS, permissions));
    }

    @RequestMapping(value = "queryProperties", method = RequestMethod.GET)
    public DataWrapper<List<PropertiesBundleDO>> queryProperties(
            @RequestParam("included") String included,
            @RequestParam(value = "excepted", required = false) String excepted
    ) {
        return DataResponse.succeed(propertiesService.query(PropertiesBundleDO.class, (Map<String, String>) JSONObject.fromObject(included), (Map<String, String>) JSONObject.fromObject(excepted)));
    }

    @RequestMapping(value = "queryRoles", method = RequestMethod.GET)
    public DataWrapper<List<RoleDO>> queryRoles(
            @RequestParam("included") String included,
            @RequestParam(value = "excepted", required = false) String excepted
    ) {
        return DataResponse.succeed(propertiesService.query(RoleDO.class, (Map<String, String>) JSONObject.fromObject(included), (Map<String, String>) JSONObject.fromObject(excepted)));
    }

    @RequestMapping(value = "queryByProperties", method = RequestMethod.GET)
    public DataWrapper<Map<String, Map<String, String>>> queryByProperties(
            @RequestParam("included") String included,
            @RequestParam(value = "excepted", required = false) String excepted
    ) {
        return DataResponse.succeed(propertiesService.queryByProperties((Map<String, String>) JSONObject.fromObject(included), (Map<String, String>) JSONObject.fromObject(excepted)));
    }

//    @RequestMapping(value = "queryLog", method = RequestMethod.GET)
//    public DataWrapper<List<LogDO>> queryLog(
//            @RequestParam(value = "begin", required = false) long begin,
//            @RequestParam(value = "end", required = false) long end,
//            @RequestParam(value = "operation", required = false) String operation,
//            @RequestParam(value = "field", required = false) String field,
//            @ModelAttribute("page") Page page
//    ) {
//        return DataResponse.succeed(propertiesService.queryLog(begin, end, operation, field, page));
//
//    }
}

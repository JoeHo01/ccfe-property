package com.ccfe.property.http.mvc.controller;

import com.ccfe.property.http.mvc.dao.MongoSupport;
import com.ccfe.property.http.mvc.entity.PropertiesBundleDO;
import com.ccfe.property.http.mvc.service.PropertiesValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("property")
public class Hello {

    private final MongoSupport mongoSupport;

    private final PropertiesValidation propertiesValidation;

    @Autowired
    public Hello(MongoSupport mongoSupport, PropertiesValidation propertiesValidation) {
        this.mongoSupport = mongoSupport;
        this.propertiesValidation = propertiesValidation;
    }

    @RequestMapping("hello")
    public String hello(){
        return "CCFE property is Running";
    }

    @RequestMapping("test")
    public List<PropertiesBundleDO> test(){
        propertiesValidation.initModel();
        propertiesValidation.validateProperties(mongoSupport.findAll(PropertiesBundleDO.class));
        return mongoSupport.findAll(PropertiesBundleDO.class);
    }
}

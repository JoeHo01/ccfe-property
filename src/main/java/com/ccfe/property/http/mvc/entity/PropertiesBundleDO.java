package com.ccfe.property.http.mvc.entity;

import org.bson.types.ObjectId;

import java.util.*;

public class PropertiesBundleDO {

    private ObjectId id;

    /** The region. */
    private String region;

    /** The brand. */
    private String brand;

    /** The scope. */
    private String scope;

    /** The name. */
    private String name;

    /** The properties. */
    private Map<String, PropertyDO> properties = new HashMap<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, PropertyDO> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, PropertyDO> properties) {
        this.properties = properties;
    }
}

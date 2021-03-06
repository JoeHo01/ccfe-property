package com.ccfe.property.http.mvc.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.*;

@Entity(value = "properties_bundle")
public class PropertiesBundleDO {

    @Id
    private ObjectId id;

    /** The region. */
    @Property("region")
    private String region;

    /** The brand. */
    @Property("brand")
    private String brand;

    /** The scope. */
    @Property("scope")
    private String scope;

    /** The name. */
    @Property("name")
    private String name;

    /** The properties. */
    @Embedded("properties")
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
